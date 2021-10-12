package com.example.citizenmecase.api.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoBaseItem(
    @SerialName("albumId")
    val albumId: Int = -1,
    @SerialName("id")
    val id: Int = -1,
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String ="",
    @SerialName("title")
    val title: String?,
    @SerialName("url")
    val url: String = ""
)