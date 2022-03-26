package com.steegler.walmart.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.steegler.walmart.domain.model.Country

data class CountryItem(
    @SerializedName("capital")
    val capital: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("currency")
    val currency: Currency,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("language")
    val language: Language,
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String
)

fun CountryItem.toCountry(): Country {
    return Country(capital, code, currency, flag, language, name, region)
}