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




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)
        minibio2 = edittext_minibio?.text.toString()
        species2 = edittext_species?.text.toString()
        facebook2 = edittext_facebook?.text.toString()
        instagram2 = edittext_instagram?.text.toString()
        twitter2 = edittext_twitter?.text.toString()
        location2 = edittext_location?.text.toString()
        ulatitude2 = LoginActivity.ulatitude
        ulongitude2 = LoginActivity.ulongitude
        aboutUs2 = edittext_about_us?.text.toString()
        issues2 = edittext_issues?.text.toString()

        getUserById()

        imagebutton_update_profile.setOnClickListener {

            minibio2 = edittext_minibio?.text.toString()
            species2 = edittext_species?.text.toString()
            facebook2 = edittext_facebook?.text.toString()
            instagram2 = edittext_instagram?.text.toString()
            twitter2 = edittext_twitter?.text.toString()
            location2 = edittext_location?.text.toString()
            ulatitude2 = LoginActivity.ulatitude
            ulongitude2 = LoginActivity.ulongitude
            aboutUs2 = edittext_about_us?.text.toString()
            issues2 = edittext_issues?.text.toString()
            updateUserById()
        }
    }


    fun updateUserById(){
        val call: Call<Void> = ServiceBuilder.create().updateUserById(LoginActivity.userid, UpdateUser(minibio2,
        species2, facebook2, instagram2, twitter2, location2, ulatitude2, ulongitude2, aboutUs2, issues2)
        )

        call.enqueue(object: Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.i("_root_ide_package_.com.stepasha.keyconservation.model.Void ", "onFailure ${t.message.toString()}")
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){



                    //   UpdateUser(userid, minibio, species,facebook,instagram,twitter,location,latitude,longitude)
                    //code goes here
                    Toast.makeText(this@UpdateProfileActivity, "Addded", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@UpdateProfileActivity, ProfileActivity::class.java)
                    startActivity(intent)

                }
                else{
                    Log.i("User ", "OnResponseFailure ${response.errorBody().toString()}")
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
                    ulatitude = LoginActivity.ulatitude
                    edittext_lat.setText(ulatitude.toString())
                    ulongitude = LoginActivity.ulongitude
                    edittext_lon.setText(ulongitude.toString())
                    aboutUs = response.body()?.about_us ?: ""
                    edittext_about_us.setText(minibio)
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
