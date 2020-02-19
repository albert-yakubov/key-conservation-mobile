package com.stepasha.endangeredhaven

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.stepasha.endangeredhaven.model.UserResult
import com.stepasha.endangeredhaven.retrofit.ServiceBuilder
import io.reactivex.annotations.Nullable
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
companion object{
    var successfulLogin: Boolean = false
    var admins : Boolean = false
    var userid: Long = 12314546
    var ulatitude: Double = 0.0
    var ulongitude: Double = 0.0
    var username4D: String = ""

}

    private var validatedUsername: Boolean = false
    private var validatedPassword: Boolean = false
    private var error: Boolean? = false
    lateinit var username: String
    lateinit var password: String


    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            validateUsername()
            validatePassword()
            getUser()
        }
        btn_register.setOnClickListener {
            val intent = Intent(this@LoginActivity, LoginScreenActivity::class.java)
            startActivity(intent)

        }

    }
    //Checks to see if the entered username is okay or not.
    private fun validateUsername(): Boolean {
        //Gets the text from the username text input layout
        username = text_input_username.editText?.text.toString().trim()

        if (username.isEmpty()) {
            text_input_username.error = "Field can't be empty"
            validatedUsername = false
            return false
        } else if (username.length < 4) {
            text_input_username.error = "Username should be at least four characters"
            return false
        } else if (username.length > 12) {
            text_input_username.error = "Username can't be more than 12 characters"
            return false
        } else {
            //Removes the error message if it already exists
            text_input_username.error = null
            text_input_username.isErrorEnabled = false
            validatedUsername = true
            return true
        }
    }
    //Checks to see if the entered password is okay or not.
    private fun validatePassword(): Boolean {
        //Gets the text from the password text input layout
        password = text_input_password.editText?.text.toString().trim()

        if (password.isEmpty()) {
            text_input_password.error = "Field can't be empty"
            validatedPassword = false
            return false
        } else if (password.length < 4) {
            text_input_password.error = "Password should be at least four characters"
            return false
        } else if (password.length > 12) {
            text_input_password.error = "Password can't be more than 12 characters"
            return false
        } else {
            //Removes the error message if it already exists
            text_input_password.error = null
            text_input_password.isErrorEnabled = false
            validatedPassword = true
            return true
        }
    }

    private fun login() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)

    }
    fun getUser(){
        username = text_input_username.editText?.text.toString().trim()


        val call: Call<UserResult> = ServiceBuilder.create().getUser(username)
        call.enqueue(object: Callback<UserResult> {
            override fun onFailure(call: Call<UserResult>, t: Throwable) {
                Log.i("Login:", "OnFailure ${t.message.toString()}")
                Toast.makeText(this@LoginActivity, "Connection Timed Out! Try Again!", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<UserResult>, response: Response<UserResult>) {
                if(response.isSuccessful) {

                    admins = response.body()?.position ?: false
                    userid = response.body()?.userid ?: 1231234
                    ulatitude = response.body()?.ulatitude ?: 0.0
                    ulongitude = response.body()?.ulongitude ?: 0.0
                    username4D = response.body()?.username ?: ""

                    Log.i("Login", "Success ${response.body()}")
        
                    Toast.makeText(this@LoginActivity, "Welcome $username", Toast.LENGTH_LONG).show()
                    successfulLogin = true

                }
                else{
                    Log.i("Login", "Failure ${response.errorBody().toString()}")
                    Toast.makeText(this@LoginActivity, "Invalid Login info!", Toast.LENGTH_LONG).show()

                    successfulLogin = false
                }

                if(successfulLogin){
                    successfulLogin = false
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }

        })
    }
}
