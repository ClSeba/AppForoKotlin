package com.example.appforo.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.appforo.data.ForoDatabase
import com.example.appforo.data.ForoRepository
import com.example.appforo.data.Tema
import kotlinx.coroutines.launch

class TemasViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ForoRepository(ForoDatabase.getInstance(app))

    val temas: LiveData<List<Tema>> = repo.observarTemas()

    private val _navegarADetalle = MutableLiveData<Long?>()
    val navegarADetalle: LiveData<Long?> = _navegarADetalle

    fun crearTema(titulo: String, cuerpo: String) {
        viewModelScope.launch {
            val id = repo.crearTema(titulo, cuerpo)
            _navegarADetalle.postValue(id)
        }
    }

    fun onNavegado() { _navegarADetalle.value = null }
}

class TemasViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TemasViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TemasViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

