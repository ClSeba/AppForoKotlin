package com.example.appforo.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ForoRepository(private val db: ForoDatabase) {
    fun observarTemas(): LiveData<List<Tema>> = db.temaDao().observarTemas()
    fun observarRespuestas(temaId: Long): LiveData<List<Respuesta>> = db.respuestaDao().observarRespuestas(temaId)

    suspend fun crearTema(titulo: String, cuerpo: String) = withContext(Dispatchers.IO) {
        db.temaDao().insertarTema(Tema(titulo = titulo, cuerpo = cuerpo))
    }

    suspend fun crearRespuesta(temaId: Long, nombre: String, texto: String) = withContext(Dispatchers.IO) {
        db.respuestaDao().insertarRespuesta(Respuesta(temaId = temaId, nombre = nombre, respuesta = texto))
        db.temaDao().incrementarConteo(temaId)
    }
}

