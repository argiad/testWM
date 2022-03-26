package com.steegler.walmart.data.remote

import com.steegler.walmart.common.Constants
import com.steegler.walmart.data.remote.dto.CountryItem
import retrofit2.http.GET

interface WalmartAPI {

    @GET(Constants.FILE_PATH)
    suspend fun getCounties(): List<CountryItem>
}