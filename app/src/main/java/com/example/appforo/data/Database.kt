package com.example.appforo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Tema::class, Respuesta::class],
    version = 1,
    exportSchema = false
)
abstract class ForoDatabase : RoomDatabase() {
    abstract fun temaDao(): TemaDao
    abstract fun respuestaDao(): RespuestaDao

    companion object {
        @Volatile
        private var INSTANCE: ForoDatabase? = null

        fun getInstance(context: Context): ForoDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ForoDatabase::class.java,
                    "foro.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}

