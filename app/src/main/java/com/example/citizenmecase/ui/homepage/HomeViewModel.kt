package com.example.citizenmecase.ui.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citizenmecase.api.model.CustomPostModel
import com.example.citizenmecase.api.model.PhotoBaseItem
import com.example.citizenmecase.api.model.PostBaseItem
import com.example.citizenmecase.repository.HomePageRepository
import com.example.citizenmecase.datasource.Resource
import com.example.citizenmecase.db.LocalRepo
import com.example.citizenmecase.db.entity.CustomPostEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homePageRepository: HomePageRepository,
    private val localRepo: LocalRepo
):ViewModel(){

    private val postList: MutableLiveData<Resource<List<PostBaseItem>>> = MutableLiveData()
    private val customPostList: MutableLiveData<Resource<List<CustomPostModel>>> = MutableLiveData()
    private val customPostListLocal: MutableLiveData<Resource<List<CustomPostModel>>> = MutableLiveData()


    fun getLocalCustomPost():LiveData<Resource<List<CustomPostModel>>>{
        return if (customPostListLocal.value?.data == null){
            viewModelScope.launch {
                homePageRepository.fetchCustomPost().collect {
                    customPostListLocal.postValue(it)
                }
            }
            customPostListLocal
        }else{
            customPostListLocal
        }
    }

    fun getPhotos(list:List<PostBaseItem>):LiveData<Resource<List<CustomPostModel>>>{
        return if(customPostList.value?.data == null){
            viewModelScope.launch {
                homePageRepository.fetchPhotos(list).collect {

                    customPostList.postValue(it)
                }
            }
            customPostList
        }else{
            customPostList
        }
    }
    fun getPosts():LiveData<Resource<List<PostBaseItem>>>{
        return if(postList.value?.data == null){
            viewModelScope.launch {
                homePageRepository.fetchPost().collect {
                    postList.postValue(it)
                }
            }
            postList
        }else{
            postList
        }
    }
}