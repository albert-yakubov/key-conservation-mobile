package com.stepasha.keyconservation

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.provider.AuthCallback
import com.auth0.android.provider.WebAuthProvider
import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import okhttp3.Credentials


class LoginActivity : AppCompatActivity() {



    lateinit var username: String
    lateinit var password: String

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            login()
        }

    }

    private fun login() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)

    }
}
