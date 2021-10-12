package com.example.citizenmecase.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "posts")
data class CustomPostEntity(

    @ColumnInfo(name = "title")val title: String = "",
    @ColumnInfo(name = "body")val body: String = "",
    @ColumnInfo(name = "userId")val userId:Int = -1,
    @ColumnInfo(name = "thumbnailUrl")val thumbnailUrl:String = "",
    @ColumnInfo(name = "url")val url:String = "",
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0

}

