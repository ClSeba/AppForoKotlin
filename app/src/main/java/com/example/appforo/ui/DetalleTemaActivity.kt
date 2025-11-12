package com.example.appforo.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appforo.R
import com.example.appforo.databinding.ActivityDetalleTemaBinding

class DetalleTemaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleTemaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalle_tema)
        binding.lifecycleOwner = this

        val temaId = intent.getLongExtra(EXTRA_TEMA_ID, -1L)
        val vm = ViewModelProvider(this, DetalleTemaViewModelFactory(application, temaId))[DetalleTemaViewModel::class.java]
        binding.vm = vm

        val adapter = RespuestasAdapter()
        binding.rvRespuestas.layoutManager = LinearLayoutManager(this)
        binding.rvRespuestas.adapter = adapter

        vm.respuestas.observe(this) { adapter.submitList(it) }

        binding.btnResponder.setOnClickListener {
            val nombre = binding.etNombre.text?.toString()?.trim().orEmpty()
            val texto = binding.etRespuesta.text?.toString()?.trim().orEmpty()
            if (nombre.isNotEmpty() && texto.isNotEmpty()) {
                vm.responder(nombre, texto)
                binding.etNombre.text?.clear()
                binding.etRespuesta.text?.clear()
            }
        }
    }

    companion object {
        const val EXTRA_TEMA_ID = "extra_tema_id"
    }
}

