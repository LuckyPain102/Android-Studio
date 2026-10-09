package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class AdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.admin_layout)

        val intent = getIntent()
        val username = intent.getStringExtra("Username")
        val txtUsername = findViewById<TextView>(R.id.txtUsername)
        txtUsername.text = "Welcome, ${username ?: "Admin"}!"

        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnLogout.setOnClickListener {
            val logoutIntent = Intent(this, LoginActivity::class.java)
            logoutIntent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(logoutIntent)
            finish()
        }
    }
}
