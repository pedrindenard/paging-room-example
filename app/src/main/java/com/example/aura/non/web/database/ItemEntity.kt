package com.example.aura.non.web.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntity(

    @ColumnInfo(name = "name")
    val name: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)