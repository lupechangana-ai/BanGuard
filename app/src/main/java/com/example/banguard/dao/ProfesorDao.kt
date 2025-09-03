package com.example.banguard.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.banguard.data.entities.Profesor

@Dao
interface ProfesorDao{
    @Insert
    suspend fun insert(profesor: Profesor)

    @Query("SELECT * FROM profesores WHERE dni = :dni LIMIT 1")
    suspend fun getByDni(dni: String): Profesor?
}