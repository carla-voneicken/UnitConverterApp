package com.example.unitconverter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.unitconverter.databinding.FragmentTemperatureBinding
import com.example.unitconverter.databinding.FragmentWeightBinding

class TemperatureFragment : Fragment() {

    private lateinit var binding: FragmentTemperatureBinding

    // Only setup the root binding in the onCreateView method
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTemperatureBinding.inflate(inflater, container, false)
        return binding.root
    }
}