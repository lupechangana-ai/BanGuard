package com.example.banguard.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.banguard.data.entities.Guardia

@Dao
interface GuardiaDao {

    @Insert
    suspend fun insert(guardia: Guardia)

    @Query("SELECT * FROM guardia WHERE usuario = :usuario AND  contraseña = :contrasena LIMIT 1 ")
    suspend fun login(usuario: String, contrasena: String): Guardia?
}
