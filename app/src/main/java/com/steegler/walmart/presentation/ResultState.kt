package com.steegler.walmart.presentation

import com.steegler.walmart.domain.model.Country

data class ResultState(
    val isLoading: Boolean = true,
    val itemsList: List<Country> = emptyList(),
    val error: String = "",
)
