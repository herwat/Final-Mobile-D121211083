package com.d121211083.bacharacters.data.sourceremote

import com.d121211083.bacharacters.data.response.GetStudentsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("api/characters/students")
    suspend fun getChar(): GetStudentsResponse
}
