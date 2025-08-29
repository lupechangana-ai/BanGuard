package com.example.banguard.data.entities

import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Entity

@Entity(
    tableName = "ingreso",
    foreignKeys = [
        ForeignKey(
            entity = Profesor :: class,
            parentColumns =
                ["id_profesor"],
            childColumns =
                ["id_profesor"],
            onDelete = ForeignKey.CASCADE),
        ForeignKey(
            entity = Guardia :: class,
            parentColumns =
                ["id_guardoas"],
            childColumns =
                ["id_guardias"],
            onDelete = ForeignKey.CASCADE)
    ]
)

data class Ingreso(
    @PrimaryKey(autoGenerate = true)
    val id_ingreso: Int=0,
    val id_profesor: Int,
    val id_guardia: Int,
    val hora_entrada: Long = System.currentTimeMillis(),
    val hora_salida: Long = System.currentTimeMillis()

)