package com.android.currencyconverterv2.presentation.splash_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.currencyconverterv2.R
import com.android.currencyconverterv2.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val DELAY_TIMER by lazy { 4300L }
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigate()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun navigate() {
        lifecycleScope.launchWhenStarted {
                delay(DELAY_TIMER)
                withContext(Dispatchers.Main){
                    findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
                }
        }
    }


}