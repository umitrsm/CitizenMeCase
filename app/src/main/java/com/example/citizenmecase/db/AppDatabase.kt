package com.example.citizenmecase.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.citizenmecase.db.entity.CustomPostEntity


@Database(entities = [CustomPostEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}