package com.example.citizenmecase.api.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomPostModel(
    @SerialName("postId")
    val postId: Int = -1,
    @SerialName("body")
    val body: String = "",
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String ="",
    @SerialName("title")
    val title: String?,
    @SerialName("url")
    val url: String = ""
)