package com.jkapps.juancamilo.loginfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val username_field: EditText = findViewById<EditText>(R.id.text_user)
        val password_field: EditText = findViewById<EditText>(R.id.text_password)
        val login_button: Button = findViewById<Button>(R.id.button)
        login_button.setOnClickListener { this.testing(username_field, password_field) }

    }

    fun testing(username_field: EditText, password_field: EditText) {
        try {
            val username = username_field.text.toString().lowercase()
            val password = password_field.text.toString()
            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Username or password are empty", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }


    }
}