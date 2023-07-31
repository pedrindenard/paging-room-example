package com.example.aura.non.web.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDao {

    @Query("SELECT * FROM items ORDER BY id ASC LIMIT :limit OFFSET :offset")
    fun getPagedList(limit: Int, offset: Int): List<ItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ItemEntity): Long

}