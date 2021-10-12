package com.example.citizenmecase.api

import com.example.citizenmecase.api.model.CommentsBaseItem
import com.example.citizenmecase.api.model.PhotoBaseItem
import com.example.citizenmecase.api.model.PostBaseItem
import retrofit2.http.GET
import retrofit2.http.Path

interface CitizenMeApi {
    @GET("photos")
    suspend fun getPhotos(): List<PhotoBaseItem>

    @GET("posts")
    suspend fun getPosts(): List<PostBaseItem>

    @GET("posts/{id}/comments")
    suspend fun getComments(@Path("id" )
                              id:Int) : List<CommentsBaseItem>
}