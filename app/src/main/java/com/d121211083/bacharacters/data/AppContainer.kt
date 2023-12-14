package com.d121211083.bacharacters.data

import com.d121211083.bacharacters.data.repository.CharRepository
import com.d121211083.bacharacters.data.sourceremote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val charRepository: CharRepository
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL = "https://api-blue-archive.vercel.app"


    val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val charRepository: CharRepository
        get() = CharRepository(retrofitService)
}


