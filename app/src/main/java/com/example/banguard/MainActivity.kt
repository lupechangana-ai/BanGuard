package com.example.banguard

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.banguard.dao.GuardiaDao
import com.example.banguard.dao.IngresoDao
import com.example.banguard.dao.ProfesorDao
import com.example.banguard.data.entities.Guardia
import com.example.banguard.database.AppDatabase
import kotlinx.coroutines.launch
import android.widget.ImageView


class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var profesorDao: ProfesorDao
    private lateinit var guardiaDao: GuardiaDao
    private lateinit var ingresoDao: IngresoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 🔹 Inicializar la base de datos
        db = AppDatabase.getDatabase(this)
        profesorDao = db.profesorDao()
        guardiaDao = db.guardiaDao()
        ingresoDao = db.ingresoDao()

        // 🔹 Ejemplo: insertar un guardia
        lifecycleScope.launch {
            guardiaDao.insert(Guardia(usuario = "Juan Pérez", dni = "jperez", contraseña = "1234"))
        }

        // 🔹 Ejemplo: buscar profesor por DNI
        lifecycleScope.launch {
            val profe = profesorDao.getByDni("12345678")
            if (profe != null) {
                Log.d("MainActivity", "Profesor encontrado: ${profe.dni}")
            } else {
                Log.d("MainActivity", "Profesor NO encontrado")
            }
        }
        val backArrow: ImageView = findViewById(R.id.flecha)
        backArrow.setOnClickListener {
            finish() // vuelve a la pantalla anterior
        }

    }
}
