package com.steegler.walmart.domain.repository

import com.steegler.walmart.domain.model.Country

interface WalmartRepo {
    suspend fun getCountries(): List<Country>
}