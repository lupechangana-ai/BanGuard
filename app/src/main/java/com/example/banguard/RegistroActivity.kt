package com.example.banguard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.banguard.database.AppDatabase
import com.example.banguard.data.entities.Guardia
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro)

        val db = AppDatabase.getDatabase(this)
        val guardiaDao = db.guardiaDao()

        val nameInput: EditText = findViewById(R.id.name_imput)
        val userInput: EditText = findViewById(R.id.user_imput)
        val passInput: EditText = findViewById(R.id.pass_imput)
        val registerBtn: Button = findViewById(R.id.register_btn)

        registerBtn.setOnClickListener {
            val name = nameInput.text.toString()
            val user = userInput.text.toString()
            val pass = passInput.text.toString()
            val state = stateInput.text.toString()

            if (name.isEmpty() || user.isEmpty() || pass.isEmpty()  ) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    guardiaDao.insert(
                        Guardia(
                            usuario = name,
                            dni = user,
                            contraseña = pass,
                            estado_guardia =

                        )
                    )
                    runOnUiThread {
                        Toast.makeText(this@RegisterActivity, "Guardia registrado con éxito", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }
}
