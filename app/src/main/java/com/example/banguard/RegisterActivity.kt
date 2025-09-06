package com.example.banguard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.banguard.data.entities.Administrador
import com.example.banguard.database.AppDatabase
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro)

        val db = AppDatabase.getDatabase(this)
        val administradorDao = db.administradorDao()

        val nameInput: EditText = findViewById(R.id.name_imput)
        val userInput: EditText = findViewById(R.id.user_imput)
        val passInput: EditText = findViewById(R.id.pass_imput)
        val registerbtn: Button = findViewById(R.id.register_btn)

        registerbtn.setOnClickListener {
            val name = nameInput.text.toString()
            val user = userInput.text.toString()
            val pass = passInput.text.toString()

            if (name.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    administradorDao.insert(
                        Administrador(
                            usuario = name,
                            contraseña = pass
                        )
                    )
                    runOnUiThread {
                        Toast.makeText(this@RegisterActivity, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }
}
