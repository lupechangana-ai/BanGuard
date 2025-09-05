package com.example.banguard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.banguard.database.AppDatabase
import com.example.banguard.dao.GuardiaDao
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var guardiaDao: GuardiaDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = AppDatabase.getDatabase(this)
        guardiaDao = db.guardiaDao()

        val loginBtn: Button = findViewById(R.id.login_btn)
        val userInput: EditText = findViewById(R.id.username_imput)
        val passInput: EditText = findViewById(R.id.password_imput)
        val noAccountTxt: TextView = findViewById(R.id.register_btn)

        loginBtn.setOnClickListener {
            val user = userInput.text.toString()
            val pass = passInput.text.toString()

            lifecycleScope.launch {
                val guardia = guardiaDao.login(user, pass)
                if (guardia != null) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            }
        }
        noAccountTxt.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}

