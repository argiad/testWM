package com.steegler.walmart.presentation.compose.country_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steegler.walmart.common.Resource
import com.steegler.walmart.domain.use_case.GetCountries
import com.steegler.walmart.presentation.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountriesListViewModel @Inject constructor(
    private val getCountries: GetCountries
) : ViewModel() {

    private val countriesEmitter = mutableStateOf(ResultState())
    val countries: State<ResultState> = countriesEmitter

    init {
        fetch()
    }

    private fun fetch() {
        getCountries().onEach { result ->
            val list = result.data ?: emptyList()

            when (result) {
                is Resource.Success -> {
                    countriesEmitter.value = ResultState(
                        isLoading = false,
                        itemsList = list
                    )
                    Log.d("Resource", "Success")
                }

                is Resource.Loading -> {
                    countriesEmitter.value = ResultState(
                        isLoading = true,
                        itemsList = list
                    )
                    Log.d("Resource", "Loading")
                }

                is Resource.Error -> {
                    countriesEmitter.value = ResultState(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                    Log.d("Resource", "Error \n ${countriesEmitter.value?.error}")
                }
            }
        }.launchIn(viewModelScope)
    }
}