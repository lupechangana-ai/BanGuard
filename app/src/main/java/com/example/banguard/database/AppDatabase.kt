package com.example.banguard.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.banguard.dao.GuardiaDao
import com.example.banguard.dao.IngresoDao
import com.example.banguard.dao.ProfesorDao
import com.example.banguard.data.entities.Guardia
import com.example.banguard.data.entities.Ingreso
import com.example.banguard.data.entities.Profesor

@Database(
    entities = [Profesor::class, Guardia::class, Ingreso::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun profesorDao(): ProfesorDao
    abstract fun guardiaDao(): GuardiaDao
    abstract fun ingresoDao(): IngresoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "institucion_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
