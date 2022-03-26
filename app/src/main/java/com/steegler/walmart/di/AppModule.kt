package com.steegler.walmart.di

import com.steegler.walmart.common.Constants
import com.steegler.walmart.data.remote.WalmartAPI
import com.steegler.walmart.data.repository.WalmartRepoImpl
import com.steegler.walmart.domain.repository.WalmartRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApi(): WalmartAPI {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WalmartAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: WalmartAPI): WalmartRepo {
        return WalmartRepoImpl(api)
    }
}