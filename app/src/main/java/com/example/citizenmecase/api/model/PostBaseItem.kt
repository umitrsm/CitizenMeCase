package com.example.citizenmecase.api.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostBaseItem(
    @SerialName("body")
    val body: String?,
    @SerialName("id")
    val postId: Int?,
    @SerialName("title")
    val title: String?,
    @SerialName("userId")
    val userId: Int?
)