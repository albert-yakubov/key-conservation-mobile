package com.stepasha.keyconservation

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.stepasha.keyconservation.adapter.RecyclerViewAdapter
import com.stepasha.keyconservation.model.Campaign
import com.stepasha.keyconservation.model.User
import com.stepasha.keyconservation.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.view_minibio
import kotlinx.android.synthetic.main.activity_update_profile.*
import kotlinx.android.synthetic.main.item_view.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {

    var facebook = ""
    var twitter = ""
    var instagram = ""
    var primaryemail = ""
    var ulatitude: Double= 0.0
    var ulongitude: Double= 0.0





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        view_lat.visibility = View.GONE
        view_lon.visibility = View.GONE


        if (!LoginActivity.admins){
           view_buttonUpdate.visibility = View.GONE



        }else if(LoginActivity.admins){
            view_buttonUpdate.visibility = View.VISIBLE

        }
        view_buttonUpdate.setOnClickListener {
            val intent = Intent(this@ProfileActivity, UpdateProfileActivity::class.java)
            startActivity(intent)
        }

        imagebutton_facebbok.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(facebook))
            startActivity(intent)
        }
        imagebutton_twitter.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(twitter))
            startActivity(intent)
        }
        imagebutton_instagram.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(instagram))
            startActivity(intent)
        }
        imagebutton_email.setOnClickListener {
                    sendEmail(primaryemail, "Hey Key", "I would really like to help with an upcoming event")
        }
        getUserById()

    }
    fun getUserById(){
        val call: Call<User> = ServiceBuilder.create().getUserById(LoginActivity.userid)

        call.enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.i("User ", "onFailure ${t.message.toString()}")
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful){

                    facebook = response.body()?.facebook ?: ""
                    twitter = response.body()?.twitter ?: ""
                    instagram = response.body()?.instagram ?: ""
                    primaryemail = response.body()?.primaryemail ?: ""
                    ulatitude = LoginActivity.ulatitude
                    view_lat.setText(ulatitude.toString())
                    ulongitude = LoginActivity.ulongitude
                    view_lon.setText(ulongitude.toString())
                    view_location.text = response.body()?.ulongitude.toString()



                    val profilePictureSfx = response.body()?.profilepicture ?: ""

                    val profilePicture: ImageView? = view_profilepicture
                    if((profilePictureSfx.endsWith("jpeg")) ||
                        (profilePictureSfx.endsWith("jpg")) ||
                        (profilePictureSfx.endsWith("png")) ||
                        (profilePictureSfx.contains("auto"))){
                        Picasso.get().load(profilePictureSfx).into(profilePicture)
                        Log.i("profile pic", profilePictureSfx)

                    }
                    view_minibio?.text = response.body()?.mini_bio ?: ""
                    view_species?.text = response.body()?.species ?:""
                    view_issues?.text = response.body()?.issues ?:""
                    view_aboutus?.text = response.body()?.about_us ?: ""
                    view_username?.text = response.body()?.username ?: ""
                    Log.i("mini bio", response.body()!!.mini_bio ?: "")
                    //code goes here
                }
                else{
                    Log.i("User ", "OnResponseFailure ${response.errorBody()}")
                }

            }

        })

    }
    private fun sendEmail(recipient: String, subject: String, message: String) {
        /*ACTION_SEND action to launch an email client installed on your Android device.*/
        val mIntent = Intent(Intent.ACTION_SEND)
        /*To send an email you need to specify mailto: as URI using setData() method
        and data type will be to text/plain using setType() method*/
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        // put recipient email in intent
        /* recipient is put as array because you may wanna send email to multiple emails
           so enter comma(,) separated emails, it will be stored in array*/
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(primaryemail))
        //put the Subject in the intent
        mIntent.putExtra(Intent.EXTRA_SUBJECT, "Hello Key")
        //put the message in the intent
        mIntent.putExtra(Intent.EXTRA_TEXT, "Dear Key")


        try {
            //start email intent
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){
            //if any thing goes wrong for example no email client application or any exception
            //get and show exception message
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }

}
