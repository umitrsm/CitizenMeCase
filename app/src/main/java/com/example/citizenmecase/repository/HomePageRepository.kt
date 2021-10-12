package com.example.citizenmecase.repository

import android.util.Log

import com.example.citizenmecase.api.CitizenMeApi
import com.example.citizenmecase.api.model.CommentsBaseItem
import com.example.citizenmecase.api.model.CustomPostModel
import com.example.citizenmecase.api.model.PhotoBaseItem
import com.example.citizenmecase.api.model.PostBaseItem
import com.example.citizenmecase.datasource.Resource
import com.example.citizenmecase.db.LocalRepo
import com.example.citizenmecase.db.entity.CustomPostEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


@ExperimentalCoroutinesApi
class HomePageRepository @Inject constructor(
    private val citizenMeApi: CitizenMeApi,
    private val localRepo: LocalRepo,
    private val ioDispatchers: CoroutineContext

): HomePageRepositorySource {

    override suspend fun fetchCustomPost(): Flow<Resource<List<CustomPostModel>>>{
       var tempList:List<CustomPostEntity> = localRepo.fetchLocalList()
        var customPostList:MutableList<CustomPostModel> = ArrayList()

        tempList.forEach {
            customPostList.add(CustomPostModel(it.id!!,it.body!!,it.thumbnailUrl,it.title,it.url))

        }
        return flow {
            emit(Resource.success(customPostList))
        }
    }

    override suspend fun fetchPost(): Flow<Resource<List<PostBaseItem>>> {
        return flow {
            emit(Resource.loading(null))
            val posts = citizenMeApi.getPosts()
            emit(Resource.success(posts))
        }.retryWhen { cause, attempt ->
            Log.d("TAG", "attempt count -> $attempt")
            Log.e("TAG", "cause -> $cause")
            (cause is Exception).also { if (it) delay(10_000) }
        }.catch {
            emit(Resource.error(it.localizedMessage, null, it))
        }.flowOn(ioDispatchers)
    }

    override suspend fun fetchPhotos(postList:List<PostBaseItem>): Flow<Resource<List<CustomPostModel>>> {
        return flow {
            emit(Resource.loading(null))
            val photos = citizenMeApi.getPhotos()
            var customPostList:MutableList<CustomPostModel> = ArrayList()
            localRepo.deleteList()
            postList.forEach {
                val id = it.postId
                val photoBaseItem: PhotoBaseItem? = photos.find { it.albumId == id }

                customPostList.add(CustomPostModel(it.postId!!,it.body!!,photoBaseItem?.thumbnailUrl!!,it.title,photoBaseItem.url))


                localRepo.insertCustomPost(CustomPostEntity(it.title.orEmpty(),it.body.orEmpty(),it.postId,photoBaseItem?.thumbnailUrl,photoBaseItem?.url))
            }

            emit(Resource.success(customPostList))
        }.retryWhen { cause, attempt ->
            Log.d("TAG", "attempt count -> $attempt")
            Log.e("TAG", "cause -> $cause")
            (cause is Exception).also { if (it) delay(10_000) }
        }.catch {
            emit(Resource.error(it.localizedMessage, null, it))
        }.flowOn(ioDispatchers)
    }

    override suspend fun fetchComments(id: Int): Flow<Resource<List<CommentsBaseItem>>> {
        return flow {
            emit(Resource.loading(null))
            val comments = citizenMeApi.getComments(id)
            emit(Resource.success(comments))
        }.retryWhen { cause, attempt ->
            Log.d("TAG", "attempt count -> $attempt")
            Log.e("TAG", "cause -> $cause")
            (cause is Exception).also { if (it) delay(10_000) }
        }.catch {
            emit(Resource.error(it.localizedMessage, null, it))
        }.flowOn(ioDispatchers)
    }




}

private operator fun Unit.invoke(customPostModel: CustomPostModel) {

}
