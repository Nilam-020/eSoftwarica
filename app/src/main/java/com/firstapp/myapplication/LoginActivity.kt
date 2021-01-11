package com.firstapp.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private lateinit var username:EditText
    private lateinit var password:EditText
    private lateinit var btnLogin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username=findViewById(R.id.username)
        password=findViewById(R.id.password)
        btnLogin=findViewById(R.id.btnLogin)


        btnLogin.setOnClickListener {
           data()
        }


    }


    private fun data() {
        val user=username.text.toString()
        val pass=password.text.toString()

        if (user == "softwarica" && pass == "coventry"){
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"Logged in Successfully",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Username or password incorrect",Toast.LENGTH_LONG).show()
        }
    }
}