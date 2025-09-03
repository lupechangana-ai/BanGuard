package com.example.banguard.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.banguard.data.entities.Ingreso

@Dao
interface IngresoDao {

    @Insert
    suspend fun insert(ingreso: Ingreso)

    @Update
    suspend fun update(ingreso: Ingreso)

    @Query("SELECT * FROM ingresos ORDER BY hora_entrada DESC")
    suspend fun getAll(): List<Ingreso>

    @Query("SELECT * FROM ingresos WHERE id_profesor = :idProfesor ORDER BY hora_entrada DESC")
    suspend fun getByProfesor(idProfesor: Int): List<Ingreso>

    @Query("UPDATE ingresos SET hora_salida = :horaSalida WHERE id_ingreso = :idIngreso")
    suspend fun registrarSalida(idIngreso: Int, horaSalida: Long)
}