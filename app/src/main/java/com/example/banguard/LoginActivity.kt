package com.example.banguard

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login) // aquí vinculamos con login.xml

        // Conectamos al TextView que pusiste en login.xml
        val tvNoCuenta: TextView = findViewById(R.id.tvNoCuenta)

        // Cuando el usuario toca el texto, se abre la otra pantalla
        tvNoCuenta.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }
}
