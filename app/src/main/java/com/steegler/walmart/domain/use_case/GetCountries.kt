package com.steegler.walmart.domain.use_case

import com.steegler.walmart.common.Resource
import com.steegler.walmart.domain.model.Country
import com.steegler.walmart.domain.repository.WalmartRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCountries @Inject constructor(
    private val repository: WalmartRepo
) {
    operator fun invoke(): Flow<Resource<List<Country>>> = flow {
        try {
            emit(Resource.Loading())
            val list = repository.getCountries()
            emit(Resource.Success(list))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}