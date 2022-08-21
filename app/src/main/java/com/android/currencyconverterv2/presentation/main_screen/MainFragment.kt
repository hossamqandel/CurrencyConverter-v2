package com.android.currencyconverterv2.presentation.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.android.currencyconverterv2.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //This fun is responsible about send events to viewModel and receive the result too
        onClick()
        collectDataV2()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClick() {
        binding.ConvertButton.setOnClickListener {
            sendEvent()
            showErrorMessage()
        }
    }


    private fun sendEvent() {
        binding.apply {
            val spFrom = spFromCurrency.selectedItem.toString()
            val spTo = spToCurrency.selectedItem.toString()
            val amount = etAmount.text.toString()
            viewModel.onConvert(spFrom, spTo, amount)
        }
    }


    private fun collectDataV2(){
        binding.apply {
            lifecycleScope.launchWhenStarted {
                viewModel.state.collect { state ->
                    val spFrom = spFromCurrency.selectedItem.toString()
                    val spTo = spToCurrency.selectedItem.toString()
                    val convertedAmount = "${state.resultDto?.convertedAmount}".take(7)
                    val convertToAmount = etAmount.text.toString() ?: ""
                    val result = "$spFrom $convertToAmount = $spTo $convertedAmount"
                    tvResult.text = result
                    progress.isVisible = state.isLoading
                }
            }
        }
    }

    private fun showErrorMessage(){
        binding.apply {
            lifecycleScope.launchWhenStarted {
                viewModel.channel.collectLatest { message ->
                    Snackbar.make(etAmount, message, 3000).show()
                }
            }
        }
    }



}