package com.example.citizenmecase.di

import android.content.Context
import androidx.room.Room
import com.example.citizenmecase.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DBModule{

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "posts"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().
            build()
    }

    @Provides
    @Singleton
    fun provideRepoDao(db: AppDatabase) = db.repoDao()
}