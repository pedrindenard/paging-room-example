package com.example.aura.non.web.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [ItemEntity::class],
    exportSchema = false,
    version = 1
)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {

        private var itemDatabase: ItemDatabase? = null

        private val callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                initialData()
            }
        }

        fun getInstance(context: Context): ItemDatabase {
            return itemDatabase ?: synchronized(this) {

                val instance = Room
                    .databaseBuilder(context, ItemDatabase::class.java, "item_database")
                    .addCallback(callback)
                    .build()

                itemDatabase = instance

                instance
            }
        }

        private fun initialData() = itemDatabase?.let { database ->
            CoroutineScope(Dispatchers.IO).launch {
                (0..1000).forEach {
                    val item = ItemEntity("Item $it")
                    database.itemDao().insert(item)
                }
            }
        }
    }
}