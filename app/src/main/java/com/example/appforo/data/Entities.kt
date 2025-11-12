package com.example.appforo.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tema")
data class Tema(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val titulo: String,
    val cuerpo: String,
    val cant_respuestas: Int = 0
)

@Entity(
    tableName = "respuesta",
    foreignKeys = [
        ForeignKey(
            entity = Tema::class,
            parentColumns = ["id"],
            childColumns = ["temaId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.NO_ACTION
        )
    ]
)
data class Respuesta(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val temaId: Long,
    val nombre: String,
    val respuesta: String
)

