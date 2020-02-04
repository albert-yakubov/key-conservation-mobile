package com.stepasha.keyconservation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.stepasha.keyconservation.model.NewCampaign
import com.stepasha.keyconservation.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.activity_create_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException


class CreatePostActivity : AppCompatActivity() {





    val photoFile: File? = null
    companion object {
        var mCurrentPhotoPath = ""
        public val IMG_CODE = 6
        var TAG = ""
        const val IMAGE_DIR_NAME = "MYNAME"

    }

    var imageUrl = ""
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

        TAG = localClassName



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
        if (requestCode == IMG_CODE && resultCode == RESULT_OK && data != null && data.data != null) {
            val uri = data.data
            var requestId: String = MediaManager.get()
                .upload(uri)
                .option("resource_type", "image")
                .option("connect_timeout", 10000)
                .option("read_timeout", 10000)
                .unsigned("xyqfim3e")
                .callback(object : UploadCallback {
                    override fun onStart(requestId: String) {
                        Log.d(TAG, "onStart")
                    }

                    override fun onProgress(requestId: String, bytes: Long, totalBytes: Long) {

                    }

                    override fun onSuccess(requestId: String, resultData: Map<*, *>) {
                        val publicId:String = resultData["url"] as String
                        mCurrentPhotoPath = publicId
                        view_event_image.setText(mCurrentPhotoPath.replace("http://", "https://"))
                        Toast.makeText(this@CreatePostActivity, "Upload successful", Toast.LENGTH_LONG).show()

                    }

                    override fun onError(requestId: String, error: ErrorInfo) {
                        Log.d(TAG,error.description)

                        Toast.makeText(this@CreatePostActivity,"Upload was not successful",Toast.LENGTH_LONG).show()
                    }

                    override fun onReschedule(requestId: String, error: ErrorInfo) {
                        Log.d(TAG, "onReschedule")
                    }
                })
                .dispatch()


            try {
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                // Log.d(TAG, String.valueOf(bitmap));
                val imageView: ImageView = findViewById<ImageView>(R.id.view_image)
                imageView.setImageBitmap(bitmap)


            } catch (e: IOException) {
                e.printStackTrace()
            }
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
