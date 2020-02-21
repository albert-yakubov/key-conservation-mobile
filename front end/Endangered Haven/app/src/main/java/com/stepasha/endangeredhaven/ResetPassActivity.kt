package com.stepasha.endangeredhaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.stepasha.endangeredhaven.model.ResetPassword
import com.stepasha.endangeredhaven.model.User
import com.stepasha.endangeredhaven.model.UserResult
import com.stepasha.endangeredhaven.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.activity_reset_pass.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResetPassActivity : AppCompatActivity() {

    var username = ""
    var password: String = ""
    var userid : Long = 235
    var minibio: String = ""
    var species:String= ""
    var facebook: String= ""
    var instagram = "instagram"
    var twitter = "twitter"
    var location :String= ""
    var ulatitude: Double = 0.0
    var ulongitude: Double = 0.0
    var aboutUs= "About Us"
    var issues = "Issues"


    var minibio2 = "I am not so nice"
    var species2= "Cats"
    var facebook2 = "facebook.com"
    var instagram2 = "instagram"
    var twitter2 = "twitter"
    var location2 = "Denver"
    var ulatitude2: Double = 0.0
    var ulongitude2: Double = 0.0
    var aboutUs2= "About Us"
    var issues2 = "Issues"
    var password2 = "1234564"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pass)


            getUser()


        btn_reset2.setOnClickListener {
            minibio2 = minibio
            species2 = species
            facebook2 = facebook
            instagram2 = instagram
            twitter2 = twitter
            location2 = location
            ulatitude2 = MainActivity.ulatitude
            ulongitude2 = MainActivity.ulongitude
            aboutUs2 = aboutUs
            issues2 = issues
            updateUserById()
        }


    }
    fun updateUserById(){
        //text_input_username.editText?.text.toString().trim()
        password2 = reset_text_input_password2.editText?.text.toString().trim()
        val call: Call<Void> = ServiceBuilder.create().updateUserPById(userid, ResetPassword(minibio2,
            species2, facebook2, instagram2, twitter2, location2, ulatitude2, ulongitude2, aboutUs2, issues2, password2))


        call.enqueue(object: Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.i("_root_ide_package_.com.stepasha.keyconservation.model.Void ", "onFailure ${t.message.toString()}")
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){



                    //   UpdateUser(userid, minibio, species,facebook,instagram,twitter,location,latitude,longitude)
                    //code goes here
                    Toast.makeText(this@ResetPassActivity, "Addded", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@ResetPassActivity, LoginActivity::class.java)
                    startActivity(intent)

                }
                else{
                    Log.i("User ", "OnResponseFailure ${response.errorBody().toString()}")
                }

            }

        })

    }
    fun getUser(){


        val call: Call<User> = ServiceBuilder.create().getUser3(LoginActivity.username)
        call.enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.i("Login:", "OnFailure ${t.message.toString()}")
                Toast.makeText(this@ResetPassActivity, "Connection Timed Out! Try Again!", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful) {

                    userid = response.body()?.userid ?: 1231234
                    minibio = response.body()?.mini_bio ?: ""
                    species = response.body()?.species ?: ""
                    facebook = response.body()?.facebook ?: ""
                    instagram = response.body()?.instagram ?: ""
                    twitter = response.body()?.twitter ?: ""
                    location = response.body()?.location ?: ""
                    ulatitude = MainActivity.ulatitude
                    ulongitude = MainActivity.ulongitude
                    aboutUs = response.body()?.about_us ?: ""
                    issues = response.body()?.issues ?: ""
                    username = response.body()?.username ?: ""
                    reset_text_input_username2.text = username
                    Log.i("Login", "Success ${response.body()}")


                }
                else{
                    Log.i("Login", "Failure ${response.errorBody().toString()}")
                    Toast.makeText(this@ResetPassActivity, "Invalid Login info!", Toast.LENGTH_LONG).show()

                }
            }

        })
    }
}
