package com.stepasha.keyconservation

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import com.stepasha.keyconservation.model.Campaign
import com.stepasha.keyconservation.model.User
import com.stepasha.keyconservation.model.UserResult
import com.stepasha.keyconservation.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.activity_connect.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsActivity : AppCompatActivity(), OnMapReadyCallback
{
    //initialize maps
    private lateinit var mMap: GoogleMap

    var username = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        //check permissions to load the map
        Companion.checkPermission(this)
        //then load pokemons
        loadCampaign()
        loadUser()
        load()
    }
    //static for location access
    var ACCESSLOCATION = 123

    fun getUserLocation(){
        Toast.makeText(this,"User Location Access on",Toast.LENGTH_LONG).show()
        //listen for current location
        var myLocation = MyLocationListener()
        //then check permissions again
        var locationManager = getSystemService(Context.LOCATION_SERVICE)as LocationManager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        } else {
            //if all good, get my location every 3 minutes within 3 feet accuracy
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 3f, myLocation)
        }
        //since my marker is constantly moving, i need my own custom thread
        var myThread = MyThread()
        myThread.start()

    }




    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            //request code this
            ACCESSLOCATION -> {
                //and all permissions granted
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    getUserLocation()

                } else {
                    Toast.makeText(this, "We can't access your location", Toast.LENGTH_LONG).show()

                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

    }

    var location:Location?=null

    // Get User location
//this is for location thats changing
    inner class MyLocationListener :LocationListener {

        //init stands for primary constructor, used when there are no other constructors
        init {
            location = Location("Start")
            location!!.longitude = 0.0
            location!!.latitude = 0.0
        }

        override fun onLocationChanged(p0: Location?) {
            location=p0
        }
        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
        override fun onProviderEnabled(p0: String?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
        override fun onProviderDisabled(p0: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
    //this is for my thread
    var oldLocation:Location?=null

    inner class MyThread : Thread() {
        init {
            oldLocation = Location("Start")
            oldLocation!!.longitude = 0.0
            oldLocation!!.latitude = 0.0
        }



        override fun run(){
            while(true){
                try {
                    if(oldLocation!!.distanceTo(location)==0f){
                        continue //continue auto jumps to most updated loop
                    }
                    oldLocation = location

                    runOnUiThread {
                        mMap.clear()

                        //show my location
                        val myLocation = LatLng(location!!.latitude, location!!.longitude)

                        mMap.addMarker(MarkerOptions()
                            .position(myLocation)
                            .title("Me")
                            .snippet("This is my Location")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.gcheck)))

                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 14f))




                    }
                    Thread.sleep(1000)
                }catch (ex:Exception){
                }
            }
        }
    }


    //load pokemons in the list (As many as you want)
    private fun loadCampaign(){
        val call: Call<MutableList<Campaign>> = ServiceBuilder.create().getAllCampaigns()

        call.enqueue(object: Callback<MutableList<Campaign>> {
            override fun onFailure(call: Call<MutableList<Campaign>>, t: Throwable) {
                Log.i("_root_ide_package_.com.stepasha.keyconservation.model.Campaigns ", "onFailure ${t.message.toString()}")
            }

            override fun onResponse(call: Call<MutableList<Campaign>>, response: Response<MutableList<Campaign>>) {
                if(response.isSuccessful){


                    val list = response.body()
                    campaigns = list
                    // for list of pokemons
                    for(i in 0 until campaigns!!.size ) {
                        var newCampaign = campaigns!![i]
                        //if not caught
                        val campaignLoc =
                            LatLng(newCampaign.latitude ?: 0.0, newCampaign.longitude ?: 0.0)

                        val campTitle = newCampaign.event_name ?: ""

                        val campDesc = newCampaign.event_description ?: ""
                        mMap.addMarker(
                            MarkerOptions()
                                .position(campaignLoc)
                                .title(campTitle)
                                .snippet(campDesc)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.gcheck))
                        )
//if you are within 2 ft of pokemon he is yours
                        /*if(location!!.distanceTo((newCampaign.location))<2){

                                newCampaign.isCatched = true
                                // mark the pokemon caught
                                listCampaigns[i]= newCampaign
                                //increase tho points
                                playerPower += newCampaign.power!!

                                Toast.makeText(applicationContext,
                                    "You have caught up a new pockemon, your new power is $playerPower",Toast.LENGTH_LONG).show()

                            }*/

                    }








                }
                else{
                    Log.i("Properties ", "OnResponseFailure ${response.errorBody()}")
                }
            }

        })

    }

    //load pokemons in the list (As many as you want)
    private fun loadUser(){
        val call: Call<MutableList<User>> = ServiceBuilder.create().getAllUsers()

        call.enqueue(object: Callback<MutableList<User>> {
            override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
                Log.i("_root_ide_package_.com.stepasha.keyconservation.model.Users ", "onFailure ${t.message.toString()}")
            }

            override fun onResponse(call: Call<MutableList<User>>, response: Response<MutableList<User>>) {
                if(response.isSuccessful){


                    val userList = response.body()
                    users = userList
                    // for list of pokemons
                    for(i in 0 until users!!.size ) {
                        var newUser = users!![i]
                        //if not caught
                        val userLoc =
                            LatLng(newUser.ulatitude ?: 0.0, newUser.ulongitude ?: 0.0)

                        ID = newUser.userid ?: 777



                        val mapUsername = newUser.username ?: ""

                        mapUsern = mapUsername
                        val mapUserDescription = newUser.mini_bio ?: ""

                        mMap.addMarker(
                                MarkerOptions()
                                    .position(userLoc)
                                    .title(mapUsername)
                                    .snippet(mapUserDescription)
                                    .visible(true)
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gcheck))
                            )





//if you are within 2 ft of pokemon he is yours
                        /*if(location!!.distanceTo((newCampaign.location))<2){

                                newCampaign.isCatched = true
                               // mark the pokemon caught
                                listCampaigns[i]= newCampaign
                                //increase tho points
                                playerPower += newCampaign.power!!

                                Toast.makeText(applicationContext,
                                    "You have caught up a new pockemon, your new power is $playerPower",Toast.LENGTH_LONG).show()

                       }*/
                    }
                }
                else{
                    Log.i("Properties ", "OnResponseFailure ${response.errorBody()}")
                }
            }

        })

    }

    private fun load(){

                campaigns
                users

    }
    //check permissions on older versions
    companion object {
        const val TAGUSER = ""
        var campaigns: MutableList<Campaign>? = null
        var users: MutableList<User>? =null
        var ID : Long = 12134556456
        var mapUsern = ""



        fun checkPermission(mapsActivity: MapsActivity){
            //check permissions on older versions
            if(Build.VERSION.SDK_INT >=23) {

                if (ActivityCompat.checkSelfPermission(
                        mapsActivity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    mapsActivity.requestPermissions(
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        mapsActivity.ACCESSLOCATION
                    )
                    return

                }
            }
            mapsActivity.getUserLocation()

        }
    }
    fun jump(): Boolean{

        return true
    }




}

