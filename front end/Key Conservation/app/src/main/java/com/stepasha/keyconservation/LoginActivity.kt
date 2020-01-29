package com.stepasha.keyconservation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.annotations.Nullable
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {



    lateinit var username: String
    lateinit var password: String

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            login()
        }
        btn_register.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)

        }

    }

    private fun login() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)

    }
}
