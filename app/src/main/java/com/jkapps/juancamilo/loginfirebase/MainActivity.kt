package com.jkapps.juancamilo.loginfirebase

import android.content.ContentValues.TAG
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance();


        val username_field: EditText = findViewById<EditText>(R.id.text_user)
        val password_field: EditText = findViewById<EditText>(R.id.text_password)
        val login_button: Button = findViewById<Button>(R.id.button)
        login_button.setOnClickListener { this.loginHandler(username_field, password_field) }

    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        // updateUI(currentUser)
    }

    fun loginHandler(username_field: EditText, password_field: EditText) {
        try {
            val username = username_field.text.toString().lowercase()
            val password = password_field.text.toString()
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username or password are empty", Toast.LENGTH_SHORT).show()
            } else {
                mAuth!!.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(
                        this
                    ) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show()
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = mAuth!!.currentUser
                            if (user != null) {
                                // Name, email address, and profile photo Url
                                val name = user.displayName
                                val email = user.email
                                val photoUrl: Uri? = user.photoUrl

                                // Check if user's email is verified
                                val emailVerified = user.isEmailVerified

                                // The user's ID, unique to the Firebase project. Do NOT use this value to
                                // authenticate with your backend server, if you have one. Use
                                // FirebaseUser.getIdToken() instead.
                                val uid = user.uid
                                val a = ""
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "The email or password are incorrect. Try Again!", task.exception)
                            Toast.makeText(
                                this, "Bad password or ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }


    }
}