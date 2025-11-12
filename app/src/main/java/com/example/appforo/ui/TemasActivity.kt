package com.example.appforo.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appforo.R
import com.example.appforo.databinding.ActivityMainBinding

class TemasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: TemasViewModel
    private lateinit var adapter: TemasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        vm = ViewModelProvider(this, TemasViewModelFactory(application))[TemasViewModel::class.java]
        binding.vm = vm

        adapter = TemasAdapter { temaId ->
            val intent = Intent(this, DetalleTemaActivity::class.java)
            intent.putExtra(DetalleTemaActivity.EXTRA_TEMA_ID, temaId)
            startActivity(intent)
        }
        binding.rvTemas.layoutManager = LinearLayoutManager(this)
        binding.rvTemas.adapter = adapter

        vm.temas.observe(this) { adapter.submitList(it) }

        binding.btnCrear.setOnClickListener {
            val titulo = binding.etTitulo.text?.toString()?.trim().orEmpty()
            val cuerpo = binding.etCuerpo.text?.toString()?.trim().orEmpty()
            if (titulo.isNotEmpty() && cuerpo.isNotEmpty()) {
                vm.crearTema(titulo, cuerpo)
                binding.etTitulo.text?.clear()
                binding.etCuerpo.text?.clear()
            }
        }
    }
}

