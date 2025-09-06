package com.example.banguard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.banguard.dao.AdministradorDao
import com.example.banguard.database.AppDatabase
import com.example.banguard.data.entities.Administrador
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var administradorDao: AdministradorDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = AppDatabase.getDatabase(this)
        administradorDao = db.administradorDao()

        val loginBtn: EditText = findViewById(R.id.name_imput)
        val userInput: EditText = findViewById(R.id.user_imput)
        val passInput: EditText = findViewById(R.id.pass_imput)
        val noAccountTxt: Button = findViewById(R.id.register_btn)

        loginBtn.setOnClickListener {
            val user = userInput.text.toString()
            val pass = passInput.text.toString()

            lifecycleScope.launch {
                val admin = administradorDao.login(user, pass)
                if (admin != null) {
                    Toast.makeText(this@LoginActivity, "Bienvenido ${admin.usuario}", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
                }
            }
        }
        noAccountTxt.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}

