package com.stepasha.endangeredhaven

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.stepasha.endangeredhaven.model.ResponseBody
import com.stepasha.endangeredhaven.model.UserResult
import com.stepasha.endangeredhaven.retrofit.ServiceBuilder
import io.reactivex.annotations.Nullable
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_reset_pass.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
companion object{
    var successfulLogin: Boolean = false
    var content_type = "application/x-www-form-urlencoded"
    //var content_type = "application/json"
    const val CLIENT_ID = "lambda-client"
    const val CLIENT_SECRET = "lambda-secret"


    var authString = "$CLIENT_ID:$CLIENT_SECRET"
    var encodedAuthString: String = Base64.encodeToString(authString.toByteArray(), Base64.NO_WRAP)
    var auth = "Basic $encodedAuthString"

     var username = ""
    lateinit var password: String
 //  var admins : Boolean = false
 //  var userid: Long = 12314546
 //  var ulatitude: Double = 0.0
 //  var ulongitude: Double = 0.0
 //  var username4D: String = ""

}

    private var validatedUsername: Boolean = false
    private var validatedPassword: Boolean = false
    private var error: Boolean? = false



    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        progress_login.visibility = View.INVISIBLE

        btn_login.setOnClickListener {
            btn_login.visibility= View.INVISIBLE
            progress_login.visibility = View.VISIBLE
            validateUsername()
            validatePassword()
            login()

        }
        btn_register.setOnClickListener {
            val intent = Intent(this@LoginActivity, LoginScreenActivity::class.java)
            startActivity(intent)

        }
        btn_reset.setOnClickListener {
            username = text_input_username.editText?.text.toString().trim()
            if (username.isEmpty()) {

                val builder = AlertDialog.Builder(this@LoginActivity)
                builder.setTitle("Empty Username")
                builder.setMessage("Please enter your username to continue")
                builder.setNegativeButton("OK"){ dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
                builder.show()

            }else{
                val intent = Intent(this@LoginActivity, ResetPassActivity::class.java)
                startActivity(intent)
            }
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

    fun login(){


        val call: Call<ResponseBody> = ServiceBuilder.create()
            .login( auth, content_type, username, password )

        call.enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                progress_login.visibility = View.INVISIBLE
                btn_login.visibility= View.VISIBLE

                Toast.makeText(this@LoginActivity, "Connection Issue... try again...", Toast.LENGTH_LONG).show()

                Log.i("Login:", "OnFailure ${t.message}")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful) {
                    btn_login.visibility= View.VISIBLE

                    progress_login.visibility = View.GONE

                    Log.i("Login", "Success ${response.body()}")


                    Toast.makeText(this@LoginActivity, "Welcome $username", Toast.LENGTH_LONG).show()
                    successfulLogin = true

                }else{
                    Log.i("Login", "Failure ${response.body()}")
                    btn_login.visibility= View.VISIBLE

                    val builder2 = AlertDialog.Builder(this@LoginActivity)
                    builder2.setTitle("Wrong Username or Password")
                    builder2.setMessage("Please check your credentials and try again")
                    builder2.setNegativeButton("OK"){ dialogInterface, _ ->
                        dialogInterface.dismiss()
                    }
                    builder2.show()

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
