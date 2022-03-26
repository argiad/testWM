package com.steegler.walmart.domain.model

import com.steegler.walmart.data.remote.dto.Currency
import com.steegler.walmart.data.remote.dto.Language

data class Country(
    val capital: String,
    val code: String,
    val currency: Currency,
    val flag: String,
    val language: Language,
    val name: String,
    val region: String
)