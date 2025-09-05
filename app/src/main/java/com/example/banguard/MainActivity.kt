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
import android.widget.Button
import android.content.Intent


class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var profesorDao: ProfesorDao
    private lateinit var guardiaDao: GuardiaDao
    private lateinit var ingresoDao: IngresoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val loginbtn = findViewById<Button>(R.id.login_btn)
        val registerbtn = findViewById<Button>(R.id.register_btn)

        loginbtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        registerbtn.setOnClickListener {
            Log.d("MainActivity", "Botón de registro presionado")
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


        db = AppDatabase.getDatabase(this)
        profesorDao = db.profesorDao()
        guardiaDao = db.guardiaDao()
        ingresoDao = db.ingresoDao()

        lifecycleScope.launch {
            guardiaDao.insert(Guardia(usuario = "Juan Pérez", dni = "jperez", contraseña = "1234" ))
        }

        lifecycleScope.launch {
            val profe = profesorDao.getByDni("12345678")
            if (profe != null) {
                Log.d("MainActivity", "Profesor encontrado: ${profe.dni}")
            } else {
                Log.d("MainActivity", "Profesor NO encontrado")
            }
        }


    }
}
