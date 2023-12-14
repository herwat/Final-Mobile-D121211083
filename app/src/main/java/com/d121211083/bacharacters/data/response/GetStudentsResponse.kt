package com.d121211083.bacharacters.data.response

import com.d121211083.bacharacters.data.models.Char
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetStudentsResponse(
    @SerialName("data")
    val data: List<Char>,
    @SerialName("message")
    val message: String
)


