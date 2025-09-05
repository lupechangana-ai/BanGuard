package com.example.banguard.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guardia")
data class Guardia (
    @PrimaryKey(autoGenerate = true)
    val id_guardia:Int=0,
    val usuario: String,
    val dni: String,
    val contraseña: String,
    val estado_guardia: String

){
    init {
        require(dni.length == 8){"El DNI debe tener 8 caracteres."}

    }
}