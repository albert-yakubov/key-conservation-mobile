package com.stepasha.keyconservation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.stepasha.keyconservation.model.UpdateUser
import com.stepasha.keyconservation.model.User
import com.stepasha.keyconservation.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.activity_update_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateProfileActivity : AppCompatActivity() {
    var userid = LoginActivity.userid
      var minibio: String = ""
      var species:String= ""
     var facebook: String= ""
      var instagram: String= ""
     var twitter : String= ""
      var location :String= ""
     var ulatitude: Double= 0.0
    var ulongitude: Double= 0.0
     var aboutUs: String=""
     var issues: String=""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)
        getUserById()

        imagebutton_update_profile.setOnClickListener {
            minibio = edittext_minibio?.text.toString()
            species = edittext_species?.text.toString()
            facebook = edittext_facebook?.text.toString()
            instagram = edittext_instagram?.text.toString()
            twitter = edittext_twitter?.text.toString()
            location = edittext_location?.text.toString()
            ulatitude = edittext_lat?.text.toString().toDouble()
            ulongitude = edittext_lon?.text.toString().toDouble()
            aboutUs = edittext_about_us?.text.toString()
            issues = edittext_issues?.text.toString()


            updateUserById()
        }
    }


    fun updateUserById(){
        val call: Call<Void> = ServiceBuilder.create().updateUserById(LoginActivity.userid, UpdateUser(LoginActivity.userid, minibio, species, facebook, instagram, twitter, location, ulatitude, "$ulongitude".toDouble(), aboutUs, issues))

        call.enqueue(object: Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.i("_root_ide_package_.com.stepasha.keyconservation.model.Void ", "onFailure ${t.message.toString()}")
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){
                    minibio = edittext_minibio?.text.toString()
                    species = edittext_species?.text.toString()
                    facebook = edittext_facebook?.text.toString()
                    instagram = edittext_instagram?.text.toString()
                    twitter = edittext_twitter?.text.toString()
                    location = edittext_location?.text.toString()
                    ulatitude = edittext_lat?.text.toString().toDouble()
                    ulongitude = edittext_lon?.text.toString().toDouble()
                    aboutUs = edittext_about_us?.text.toString()
                    issues = edittext_issues?.text.toString()

                 //   UpdateUser(userid, minibio, species,facebook,instagram,twitter,location,latitude,longitude)
                    //code goes here
                    Toast.makeText(this@UpdateProfileActivity, "Addded", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@UpdateProfileActivity, MainActivity::class.java)
                    startActivity(intent)

                }
                else{
                    Log.i("User ", "OnResponseFailure ${response.errorBody()}")
                }

            }

        })

    }
    fun getUserById(){
        val call: Call<User> = ServiceBuilder.create().getUserById(LoginActivity.userid)

        call.enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.i("User ", "onFailure ${t.message.toString()}")
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful){
                    minibio = response.body()?.mini_bio ?: ""
                    edittext_minibio.setText(minibio)
                    species = response.body()?.species ?: ""
                    edittext_species.setText(species)
                    facebook = response.body()?.facebook ?: ""
                    edittext_facebook.setText(facebook)
                    instagram = response.body()?.instagram ?: ""
                    edittext_instagram.setText(instagram)
                    twitter = response.body()?.twitter ?: ""
                    edittext_twitter.setText(twitter)
                    location = response.body()?.location ?: ""
                    edittext_location.setText(location)
                    ulatitude = response.body()?.ulatitude ?: 0.0
                    edittext_lat.setText(ulatitude.toString())
                    ulongitude = response.body()?.ulongitute ?: -104.91650000000358
                    edittext_lon.setText(ulongitude.toString())
                    aboutUs = response.body()?.about_us ?: ""
                    edittext_minibio.setText(minibio)
                    issues = response.body()?.issues ?: ""
                    edittext_issues.setText(issues)
                    Log.i("mini bio", response.body()!!.mini_bio ?: "")
                    //code goes here
                }
                else{
                    Log.i("User ", "OnResponseFailure ${response.errorBody()}")
                }

            }

        })

    }
}
