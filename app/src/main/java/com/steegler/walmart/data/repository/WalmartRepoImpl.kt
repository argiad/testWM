package com.steegler.walmart.data.repository

import com.steegler.walmart.data.remote.WalmartAPI
import com.steegler.walmart.data.remote.dto.toCountry
import com.steegler.walmart.domain.model.Country
import com.steegler.walmart.domain.repository.WalmartRepo
import javax.inject.Inject

class WalmartRepoImpl @Inject constructor(
    private val api: WalmartAPI
) : WalmartRepo {

    override suspend fun getCountries(): List<Country> {
        return api.getCounties().map { it.toCountry() }
    }
}