package com.example.appforo.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.appforo.data.ForoDatabase
import com.example.appforo.data.ForoRepository
import com.example.appforo.data.Respuesta
import kotlinx.coroutines.launch

class DetalleTemaViewModel(app: Application, private val temaId: Long) : AndroidViewModel(app) {
    private val repo = ForoRepository(ForoDatabase.getInstance(app))

    val respuestas: LiveData<List<Respuesta>> = repo.observarRespuestas(temaId)

    fun responder(nombre: String, texto: String) {
        viewModelScope.launch { repo.crearRespuesta(temaId, nombre, texto) }
    }
}

class DetalleTemaViewModelFactory(
    private val app: Application,
    private val temaId: Long
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetalleTemaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetalleTemaViewModel(app, temaId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

