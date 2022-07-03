package com.example.androidtask.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.androidtask.common.Constant.DATABASE_NAME
import com.example.androidtask.domain.model.AlbumsItem
import com.example.androidtask.domain.model.Photos


@Database(entities = [AlbumsItem::class, Photos::class], version = 1, exportSchema = false)
@TypeConverters
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAlbums(): DatabaseDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }


        private fun buildDatabase(context: Context): AppDatabase {

            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                }).build()
        }
    }
}