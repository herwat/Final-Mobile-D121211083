package com.d121211083.bacharacters.data.repository

import com.d121211083.bacharacters.data.response.GetStudentsResponse
import com.d121211083.bacharacters.data.sourceremote.ApiService


class CharRepository (private val apiService: ApiService) {
    suspend fun getChar(): GetStudentsResponse {
        return apiService.getChar()
    }
}