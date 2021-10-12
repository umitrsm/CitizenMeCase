package com.example.citizenmecase.api.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentsBaseItem(
    @SerialName("body")
    val body: String?,
    @SerialName("email")
    val email: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("postId")
    val postId: Int?
)