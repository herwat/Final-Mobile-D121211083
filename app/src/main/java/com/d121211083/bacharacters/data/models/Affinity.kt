package com.d121211083.bacharacters.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Affinity(
    val indoors: String,
    val indoorsEmotion: String,
    val outdoors: String,
    val outdoorsEmotion: String,
    val urban: String,
    val urbanEmotion: String
): Parcelable