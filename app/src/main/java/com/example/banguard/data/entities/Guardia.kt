package com.example.banguard.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guardia")
data class Guardia (
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val dni: String,
    val contraseña: String
)