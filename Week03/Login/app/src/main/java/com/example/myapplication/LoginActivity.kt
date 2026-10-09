
package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: SampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)
            .get(SampleViewModel::class.java)

        observeViewModel()

        val txtUser = findViewById<EditText>(R.id.txtUsername)
        val txtPass = findViewById<EditText>(R.id.txtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener { v ->
            when (v.id) {
                R.id.btnLogin -> {
                    if (txtUser.text.toString() == "QuangNN" &&
                        txtPass.text.toString() == "123"
                    ) {
                        viewModel.incrementBadgeCount()

                        val intent = Intent(this, AdminActivity::class.java)
                        intent.putExtra("Username", txtUser.text.toString())
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this,
                            "Not correct",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun observeViewModel() {
        viewModel.badgeCount.observe(this, Observer {
            showToast(it)
        })
    }

    private fun showToast(value: Int) {
        Toast.makeText(
            this,
            value.toString(),
            Toast.LENGTH_LONG
        ).show()
    }
}

