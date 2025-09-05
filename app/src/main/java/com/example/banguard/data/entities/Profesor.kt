package com.example.banguard.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profesores")
data class Profesor(
    @PrimaryKey(autoGenerate = true)
    val id_profesor:Int=0,
    val dni:String,
    val qr_code:String? = null,
    val estado_Profesor: String
)