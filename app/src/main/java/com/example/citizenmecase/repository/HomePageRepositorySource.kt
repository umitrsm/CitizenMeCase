package com.example.citizenmecase.repository


import com.example.citizenmecase.api.model.CommentsBaseItem
import com.example.citizenmecase.api.model.CustomPostModel
import com.example.citizenmecase.api.model.PhotoBaseItem
import com.example.citizenmecase.api.model.PostBaseItem
import com.example.citizenmecase.datasource.Resource
import com.example.citizenmecase.db.entity.CustomPostEntity
import kotlinx.coroutines.flow.Flow

interface HomePageRepositorySource {

    suspend fun fetchPost(): Flow<Resource<List<PostBaseItem>>>

    suspend fun fetchPhotos(list:List<PostBaseItem>): Flow<Resource<List<CustomPostModel>>>

    suspend fun fetchComments(id:Int): Flow<Resource<List<CommentsBaseItem>>>

    suspend fun fetchCustomPost(): Flow<Resource<List<CustomPostModel>>>

}