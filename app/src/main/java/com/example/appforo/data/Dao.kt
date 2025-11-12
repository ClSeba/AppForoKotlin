package com.example.appforo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface TemaDao {
    @Query("SELECT * FROM tema ORDER BY id DESC")
    fun observarTemas(): LiveData<List<Tema>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTema(tema: Tema): Long

    @Query("UPDATE tema SET cant_respuestas = cant_respuestas + 1 WHERE id = :temaId")
    suspend fun incrementarConteo(temaId: Long)
}

@Dao
interface RespuestaDao {
    @Query("SELECT * FROM respuesta WHERE temaId = :temaId ORDER BY id ASC")
    fun observarRespuestas(temaId: Long): LiveData<List<Respuesta>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarRespuesta(respuesta: Respuesta): Long
}

