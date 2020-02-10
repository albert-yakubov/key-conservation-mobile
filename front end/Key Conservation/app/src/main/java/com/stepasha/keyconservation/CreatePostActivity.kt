package com.stepasha.keyconservation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.stepasha.keyconservation.model.CampUser
import com.stepasha.keyconservation.model.NewCampUser
import com.stepasha.keyconservation.model.NewCampaign
import com.stepasha.keyconservation.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.activity_create_post.*
import kotlinx.android.synthetic.main.item_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class CreatePostActivity : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {



    val photoFile: File? = null
    companion object {
        var mCurrentPhotoPath = ""
        public val IMG_CODE = 6
        var TAG = ""
        var TAGS = ""
        const val IMAGE_DIR_NAME = "MYNAME"

    }
    //location:
    private var mLatitudeTextView = ""
    private var mLongitudeTextView = ""
    //tools that I need API client for connection listening
    private var mGoogleApiClient: GoogleApiClient? = null
    //extracting location
    private var mLocation: Location? = null
    //location manager
    private var mLocationManager: LocationManager? = null
    //location listeners
    private var mLocationRequest: LocationRequest? = null
    private val listener: com.google.android.gms.location.LocationListener? = null
    //update intervals
    private val UPDATE_INTERVAL = (4 * 1000).toLong()  /* 40 secs */
    private val FASTEST_INTERVAL: Long = 10000 /* 10 sec */
    private var locationManager: LocationManager? = null
    private val isLocationEnabled: Boolean
        get() {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager!!.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )
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

    @SuppressLint("WrongThread", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        //initiating date within saving the post

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")

        val date  = LocalDateTime.now()
        val format = DateTimeFormatter.ofPattern("yyyyMMdd")
        val giveMeTime = date.format(format).toString()
        view_created_at.editText?.setText(giveMeTime)

        TAG = localClassName

        mLatitudeTextView = view_lat_camp.editText?.text.toString()
        mLongitudeTextView = view_lon_camp.editText?.text.toString()
        //set callbacks on connection to API
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()
        mLocationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        Log.d("no", "no connection to API")
        checkLocation() //check whether location service is enable or not in your  phone

        pb_add_property.visibility = View.GONE

        view_image.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, IMG_CODE)

            }

            btn_property_add.setOnClickListener {
                title = view_textTitle.editText?.text.toString()
                banner_image = view_status.editText?.text.toString()
                location = view_textLocation.editText?.text.toString()
                created_at = giveMeTime
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
                mLatitudeTextView.toDouble(),
                mLongitudeTextView.toDouble(),
                created_at,
                event_image,
                event_name,
                event_description,
                NewCampUser(LoginActivity.userid)
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
                        mLatitudeTextView.toDouble(),
                        mLongitudeTextView.toDouble(),
                        created_at,
                        event_image,
                        event_name,
                        event_description,
                        NewCampUser(LoginActivity.userid)

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
    //for location
    //this is the same as overriding onRequest permission result
    override fun onConnected(p0: Bundle?) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling

            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return

        }

        startLocationUpdates()
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)
        if (mLocation == null) {
            startLocationUpdates()
        }
        if (mLocation != null) {
            // mLatitudeTextView.setText(String.valueOf(mLocation.getLatitude()));
            //mLongitudeTextView.setText(String.valueOf(mLocation.getLongitude()));
        } else {
            Toast.makeText(this, "Location not Detected", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onConnectionSuspended(i: Int) {
        Log.i(TAGS, "Connection Suspended")
        mGoogleApiClient!!.connect()
    }
    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.i(TAGS, "Connection failed. Error: " + connectionResult.errorCode)
    }

    override fun onStart() {
        super.onStart()
        if (mGoogleApiClient != null) {
            mGoogleApiClient!!.connect()
        }
    }

    override fun onStop() {
        super.onStop()
        if (mGoogleApiClient!!.isConnected) {
            mGoogleApiClient!!.disconnect()
        }
    }

    //location listener

    override fun onLocationChanged(location: Location) {
        val msg = "Updated Location: " +
                location.latitude.toString() + "," +
                location.longitude.toString()
        mLatitudeTextView = location.latitude.toString()
        mLongitudeTextView = location.longitude.toString()
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        // You can now create a LatLng Object for use with maps
        val latLng = LatLng(location.latitude, location.longitude)
    }
    //location request is being used here for location updates
    private fun startLocationUpdates() {
        // Create the location request
        mLocationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(UPDATE_INTERVAL)
            .setFastestInterval(FASTEST_INTERVAL)
        // Request location updates
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling

            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
            mGoogleApiClient,
            mLocationRequest, this
        )
        Log.d("reque", "--->>>>")
    }

    //function to build custom alert box
    private fun showAlert() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Enable Location")
            .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " + "use this app")
            .setPositiveButton("Location Settings") { paramDialogInterface, paramInt ->
                //send user to the settings screen to change the permission
                val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(myIntent)
            }
            .setNegativeButton("Cancel") { paramDialogInterface, paramInt -> }
        dialog.show()
    }
    private fun checkLocation(): Boolean {
        if (!isLocationEnabled)
            showAlert()
        return isLocationEnabled
    }

}
