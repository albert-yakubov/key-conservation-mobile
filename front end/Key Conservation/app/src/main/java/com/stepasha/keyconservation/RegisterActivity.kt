package com.stepasha.keyconservation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.stepasha.keyconservation.model.NewUser
import com.stepasha.keyconservation.model.RegisterResponse
import com.stepasha.keyconservation.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    companion object{
        var token = ""
    }


    //validators (stuff like how long the password can be and so on)
    private var validatedFirstName: Boolean = false
    private var validatedLastName: Boolean = false
    private var validatedUsername: Boolean = false
    private var validatedEmail: Boolean = false
    private var validatedPassword: Boolean = false


    lateinit var firstname: String
    lateinit var lastname: String
    lateinit var username: String
    lateinit var primaryemail: String
    lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_register_create.setOnClickListener {
            validateFirstName()
            validateLastName()
            validateUsername()
            validateEmail()
            validatePassword()
            confirmRegister()
        }
        btn_cancel_registration.setOnClickListener {
            finish()
        }
    }
    //Checks to see if the entered first name is okay or not.
    private fun validateFirstName(): Boolean {
        //Gets the text from the firstName text input layout
        firstname = text_input_first_name_register.editText?.text.toString().trim()

        if (firstname.isEmpty()) {
            text_input_first_name_register.error = "Field can't be empty"
            validatedFirstName = false
            return false
        } else if (firstname.length < 2) {
            text_input_first_name_register.error = "First name must be at least two characters"
            return false
        } else {
            //Removes the error message if it already exists
            text_input_first_name_register.error = null
            text_input_first_name_register.isErrorEnabled = false
            validatedFirstName = true
            return true
        }
    }
    //Checks to see if the entered last name is okay or not.
    private fun validateLastName(): Boolean {
        //Gets the text from the lastName text input layout
        lastname = text_input_last_name_register.editText?.text.toString().trim()

        if (lastname.isEmpty()) {
            text_input_last_name_register.error = "Field can't be empty"
            validatedLastName = false
            return false
        } else if (lastname.length < 2) {
            text_input_last_name_register.error = "Last name must be at least two characters"
            return false
        } else {
            //Removes the error message if it already exists
            text_input_last_name_register.error = null
            text_input_last_name_register.isErrorEnabled = false
            validatedLastName = true
            return true
        }
    }
    //Checks to see if the entered email is okay or not.
    private fun validateEmail(): Boolean {
        //Gets the text from the email text input layout
        primaryemail = text_input_email_register.editText?.text.toString().trim()

        if (primaryemail.isEmpty()) {
            text_input_email_register.error = "Field can't be empty"
            validatedEmail = false
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(primaryemail).matches()) {
            text_input_email_register.error = "Invalid Email Format"
            return false
        } else {
            //Removes the error message if it already exists
            text_input_email_register.error = null
            text_input_email_register.isErrorEnabled = false
            validatedEmail = true
            return true
        }
    }
    //Checks to see if the entered username is okay or not.
    private fun validateUsername(): Boolean {
        //Gets the text from the username text input layout
        username = text_input_username_register.editText?.text.toString().trim()

        if (username.isEmpty()) {
            text_input_username_register.error = "Field can't be empty"
            validatedUsername = false
            return false
        } else if (username.length < 4) {
            text_input_username_register.error = "Username must be at least four characters"
            return false
        }

        //As of the current time of this else if statement, the current max characters is six.
        //Backend person said that he would change it to 12 in the future.
        else if (username.length > 12) {
            text_input_username_register.error = "Username can't be more than 12 characters"
            return false
        } else {
            //Removes the error message if it already exists
            text_input_username_register.error = null
            text_input_username_register.isErrorEnabled = false
            validatedUsername = true
            return true
        }
    }
    //Checks to see if the entered password is okay or not.
    private fun validatePassword(): Boolean {
        //Gets the text from the password text input layout
        password = text_input_password_register.editText?.text.toString().trim()

        if (password.isEmpty()) {
            text_input_password_register.error = "Field can't be empty"
            validatedPassword = false
            return false
        } else if (password.length < 4) {
            text_input_password_register.error = "Password must be at least four characters"
            return false
        } else if (password.length > 12) {
            text_input_password_register.error = "Password can't be more than 12 characters"
            return false
        } else {
            //Removes the error message if it already exists
            text_input_password_register.error = null
            text_input_password_register.isErrorEnabled = false
            validatedPassword = true
            return true
        }
    }
    //Checks to see if all the fields are correct or not. If so, return back to the login page.
    private fun confirmRegister() {
        //If any of the entered information isn't entered properly, prevent the user from successfully registering.
        if (!validatedFirstName || !validatedLastName || !validatedUsername || !validatedEmail || !validatedPassword)
            return

        Toast.makeText(
            this,
            "New User successfully created\nWelcome $firstname",
            Toast.LENGTH_SHORT
        ).show()
        createUserr()
        finish()
    }
    private fun createUserr(){
        val call: Call<RegisterResponse> = ServiceBuilder.create().createUser(NewUser(firstname,lastname,primaryemail, username,password))

        call.enqueue(object: Callback<RegisterResponse> {
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.i("OnFailure", t.message)
            }

            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                token = response.body()?.token ?: ""
                Log.i("onRespone", token)
            }
        })
    }
}
