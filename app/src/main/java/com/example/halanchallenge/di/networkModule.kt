package com.example.halanchallenge.di

import com.example.halanchallenge.data.remote.ChallengeApiService
import com.example.halanchallenge.data.remote.ChallengeApiService.Companion.BASE_URL
import com.example.halanchallenge.data.repositories.ChallengeRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {


    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
//            .client(getOkHttpClient(androidContext()))
            .build()
            .create(ChallengeApiService::class.java)
    }
    single {
        ChallengeRepository(get())
    }
}