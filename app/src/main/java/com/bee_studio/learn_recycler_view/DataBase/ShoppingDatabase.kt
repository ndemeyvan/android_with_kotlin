package com.bee_studio.learn_recycler_view.DataBase

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.bee_studio.learn_recycler_view.Dao.ShoppingDao
import com.bee_studio.learn_recycler_view.Entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract fun getShoppingDao(): ShoppingDao

    //companion object est equivalent a static en java
    companion object {
        //Volatile veux dire que cette instance ne
        // sera pas disponible que pour un thread a la fois
        @Volatile
        private var instance: ShoppingDatabase? = null;
        private val LOCK = Any()
        //synchronized Pour que les thread ny accede pas simultanement
        //invoke et Operator font a ce que ce bout de code soit
        // appele a chaque fois que cette classe sera extensie
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?: createDataBase(context).also {
                instance=it
            }
        }
        private fun createDataBase(context: Context)=Room.databaseBuilder(
            context.applicationContext,
            ShoppingDatabase::class.java,
            "ShoppingDb.db"
        ).build()
    }

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }


}