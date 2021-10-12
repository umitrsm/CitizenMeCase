package com.example.citizenmecase.db

import com.example.citizenmecase.db.entity.CustomPostEntity
import javax.inject.Inject

class LocalRepo @Inject constructor(
    private val repoDao: RepoDao

) {
   suspend fun insertCustomPost(customPostItem: CustomPostEntity) = repoDao.insertCustomPost(customPostItem)

    suspend fun removeFav(id: Int) = repoDao.delete(id)

    suspend fun isExist(node_id:String) = repoDao.exists(node_id)

    suspend fun fetchLocalList() = repoDao.fetchLocalList()

    suspend fun deleteList() = repoDao.deleteList()

}