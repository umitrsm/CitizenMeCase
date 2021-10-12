package com.example.citizenmecase.db

import androidx.room.*
import com.example.citizenmecase.db.entity.CustomPostEntity


@Dao
interface RepoDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomPost(customPostItem: CustomPostEntity)


    @Query("DELETE FROM posts WHERE id = :uid ")
    suspend fun delete(uid:Int)

    @Query("SELECT EXISTS (SELECT 1 FROM posts WHERE title = :name )")
    suspend fun exists(name: String): Boolean

    @Query("SELECT * from posts")
    suspend fun fetchLocalList() : List<CustomPostEntity>

    @Query("DELETE  from posts")
    suspend fun deleteList()
}