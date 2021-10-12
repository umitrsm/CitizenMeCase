package com.example.citizenmecase.ui.postdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citizenmecase.api.model.CommentsBaseItem
import com.example.citizenmecase.api.model.PostBaseItem
import com.example.citizenmecase.datasource.Resource
import com.example.citizenmecase.db.LocalRepo
import com.example.citizenmecase.repository.HomePageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class PostViewModel  @Inject constructor(
    private val homePageRepository: HomePageRepository,

): ViewModel(){

    private val commandsList: MutableLiveData<Resource<List<CommentsBaseItem>>> = MutableLiveData()


    fun getCommands(id:Int): LiveData<Resource<List<CommentsBaseItem>>> {
        return if(commandsList.value?.data == null){
            viewModelScope.launch {
                homePageRepository.fetchComments(id).collect {
                    commandsList.postValue(it)
                }
            }
            commandsList
        }else{
            commandsList
        }
    }
}