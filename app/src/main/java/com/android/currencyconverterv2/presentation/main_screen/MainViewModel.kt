package com.android.currencyconverterv2.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.currencyconverterv2.core.util.Resource
import com.android.currencyconverterv2.domain.use_case.CurrencyConvertUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val notNullConvertUseCase: CurrencyConvertUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CurrencyState()
    )
    val state: StateFlow<CurrencyState> = _state.asStateFlow()

    private val _channel = Channel<String>()
    val channel = _channel.receiveAsFlow()

    fun onConvert(from: String, to: String, amount: String) {
        notNullConvertUseCase.invoke(from, to, amount).onEach { Resource ->
            when (Resource) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value =
                        state.value.copy(
                            resultDto = Resource.data?.result,
                            isLoading = false
                        )
                }

                is Resource.Error -> {
                    val errorMessage = Resource.message
                    _channel.send(errorMessage ?: "Unknown occurred error")
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}