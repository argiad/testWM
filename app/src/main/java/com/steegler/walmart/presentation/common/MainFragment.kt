package com.steegler.walmart.presentation.common

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.steegler.walmart.R
import com.steegler.walmart.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<MainFragmentBinding>(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.rvCountries.layoutManager = LinearLayoutManager(context)
        binding.rvCountries.adapter = CountriesAdapter()


        viewModel.countries.observe(viewLifecycleOwner) { value ->
            (binding.rvCountries.adapter as? CountriesAdapter)?.let { it.update(value.itemsList) }
        }
        return binding.root
    }
}