package com.steegler.walmart.presentation.common

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.steegler.walmart.common.Resource
import com.steegler.walmart.domain.use_case.GetCountries
import com.steegler.walmart.presentation.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCountries: GetCountries,
    application: Application
) : AndroidViewModel(application) {

    private val countriesEmitter = MutableLiveData<ResultState>()
    val countries: LiveData<ResultState> = countriesEmitter


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