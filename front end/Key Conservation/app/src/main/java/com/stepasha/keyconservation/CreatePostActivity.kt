package com.stepasha.keyconservation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cloudinary.android.MediaManager
import com.stepasha.keyconservation.model.NewCampaign
import com.stepasha.keyconservation.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.activity_create_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.net.URI


class CreatePostActivity : AppCompatActivity() {
//    val mmm = MediaManager.init(this)
 //   var requestId = MediaManager.get().upload("imageFile.jpg").dispatch()
    val photoFile: File? = null
    companion object {
        var mCurrentPhotoPath = ""
        public val IMG_CODE = 6
        const val IMAGE_DIR_NAME = "MYNAME"

    }

    private var imageFilePath = ""
    lateinit var title: String
    lateinit var banner_image: String
    lateinit var location: String
    lateinit var created_at: String
    lateinit var event_image: String
    lateinit var event_name: String
    lateinit var event_description: String
    //encode image to base64 string

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)



        pb_add_property.visibility = View.GONE

        button_image_add.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, IMG_CODE)

            }

            btn_property_add.setOnClickListener {
                title = view_textTitle.editText?.text.toString()
                banner_image = view_bannerImage.editText?.text.toString()
                location = view_textLocation.editText?.text.toString()
                created_at = view_created_at.editText?.text.toString()
                event_image = view_event_image_layout.editText?.text.toString()
                event_name = view_event_name.editText?.text.toString()
                event_description = view_event_description.editText?.text.toString()
                createPost()
            }
        }
    }

    fun createPost() {
        pb_add_property?.visibility = View.VISIBLE
        val call: Call<Void> = ServiceBuilder.create().createNewCampaign(
            NewCampaign(
                title,
                banner_image,
                location,
                created_at.toLong(),
                event_image,
                event_name,
                event_description
            )
        )
        call.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.i("Add Property", "OnFailure ${t.message}")
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.i("Add Post", "OnResponseSuccess ${response.message()}")
                    val newPost = NewCampaign(
                        title,
                        banner_image,
                        location,
                        created_at.toLong(),
                        event_image,
                        event_name,
                        event_description
                    )
                    //     LoginActivity.properties?.plus(nProperty)
                    pb_add_property?.visibility = View.GONE
                    Toast.makeText(this@CreatePostActivity, "Addded", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@CreatePostActivity, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@CreatePostActivity, "NOT Addded", Toast.LENGTH_LONG)
                        .show()
                    pb_add_property?.visibility = View.GONE
                    Log.i("Add Property", "OnResponseFailure ${response.errorBody()}")
                }
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
         if (resultCode == IMG_CODE && resultCode == Activity.RESULT_OK){
         val bitmap: Bitmap = BitmapFactory.decodeFile(photoFile?.absolutePath)
                    view_image.setImageBitmap(bitmap)
                if (photoFile != null) {
                    val uri: Uri = Uri.fromFile(photoFile)
                    view_event_image.setText(uri.toString())
                }else{
                    Toast.makeText(this, "no image", Toast.LENGTH_LONG).show()
                }

            }
    }






}
