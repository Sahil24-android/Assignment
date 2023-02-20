package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.assignment.data.PrefManager
import okhttp3.*
import okio.IOException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var prefManager: PrefManager
    private val client = OkHttpClient()
    private lateinit var email:TextView
    private lateinit var password:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        prefManager = PrefManager(this)

        val loginButton = findViewById<Button>(R.id.login_button)
        loginButton.setOnClickListener {
            // Get the email and password from the input fields
            email = findViewById<EditText>(R.id.email_login)
            password = findViewById<EditText>(R.id.password_login)
            val requestBody = FormBody.Builder()
                .add("email", email.text.toString())
                .add("password", password.text.toString())
                .add("device_id", "exzcde")
                .build()

            val request = Request.Builder()
                .url("https://telugumatches.in/api/login")
                .post(requestBody)
                .build()

            // Make the API call
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    // Handle network errors here
                }

                override fun onResponse(call: Call, response: Response) {
                    val responseBody = response.body?.string()

                    if (response.isSuccessful && responseBody != null) {
                        handleLoginResponse(responseBody)
                    } else {
                        handleLoginError()
                    }
                }
            })
        }
    }


    private fun handleLoginResponse(responseBody: String) {
        val jsonObject = JSONObject(responseBody)
        val status = jsonObject.getString("status")
        if (status == "success") {
            prefManager.setLogin(true)
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        } else {
            val errorMessage = jsonObject.getString("message")
            runOnUiThread {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                email.text = null
                password.text = null
            }
        }

    }

    private fun handleLoginError() {
        runOnUiThread {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
            email.text = null
            password.text = null
        }
    }
}
