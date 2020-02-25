package com.stepasha.endangeredhaven

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.stepasha.endangeredhaven.Util.AppController
import com.stepasha.endangeredhaven.adapter.RecyclerViewAdapter
import com.stepasha.endangeredhaven.model.Campaign
import com.stepasha.endangeredhaven.model.UserResult
import com.stepasha.endangeredhaven.retrofit.LoginServiceSql
import com.stepasha.endangeredhaven.retrofit.ServiceBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    private lateinit var disposable: Disposable

    private lateinit var disposable2: Disposable

    @Inject
    lateinit var foundUserService: LoginServiceSql
    companion object{
        var campaign: MutableList<Campaign>? = null

        var admins : Boolean = false
        var userid: Long = 12314546
        var ulatitude: Double = 0.0
        var ulongitude: Double = 0.0
        var username4D: String = ""
        lateinit var username: String
    }

    lateinit var password: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getUser()

        navigation.setOnNavigationItemSelectedListener(this)

        (application as AppController).appComponent.inject(this)
        searchButton.setOnClickListener {
            val searchedFor = enterText.text.toString()
            if (searchedFor.isNotEmpty() && searchedFor.contains(searchedFor)) {

                disposable = foundUserService.getFoundUser(searchedFor)

                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ title   ->
                        if (title.isNotEmpty() ) {
                            vRecycle.adapter = RecyclerViewAdapter(title)
                        } else {
                            Toast.makeText(this, "title not found", Toast.LENGTH_SHORT).show()
                        }
                    }, { t ->
                        Log.i("Retrofit - ", "$t", t)
                    })
            }else {
                Toast.makeText(this, "Please enter something in search", Toast.LENGTH_SHORT).show()
            }
        }
        searchButton.setOnLongClickListener {
            val searchedFor = enterText.text.toString()
            if (searchedFor.isNotEmpty() && searchedFor.contains(searchedFor)) {

                disposable2 = foundUserService.getEventName(searchedFor)

                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ eventname   ->
                        if (title.isNotEmpty() ) {
                            vRecycle.adapter = RecyclerViewAdapter(eventname)
                        } else {
                            Toast.makeText(this, "event not found", Toast.LENGTH_SHORT).show()
                        }
                    }, { t ->
                        Log.i("Retrofit - ", "$t", t)
                    })
            }else {
                Toast.makeText(this, "Please enter something in search", Toast.LENGTH_SHORT).show()
            }
           return@setOnLongClickListener true

        }


        if (!admins){
            view_floatingbutton.visibility = View.GONE
        }else if(admins){
            view_floatingbutton.visibility = View.VISIBLE
        }

        view_floatingbutton.setOnClickListener {
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        }

        getAllCampaigns()


    }

    override fun onStart() {
        super.onStart()
        if (!admins){
            view_floatingbutton.visibility = View.GONE
        }else if(admins){
            view_floatingbutton.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        if (!admins){
            view_floatingbutton.visibility = View.GONE
        }else if(admins){
            view_floatingbutton.visibility = View.VISIBLE
        }
    }
    fun getUser(){


        val call: Call<UserResult> = ServiceBuilder.create().getUser(LoginActivity.username)
        call.enqueue(object: Callback<UserResult> {
            override fun onFailure(call: Call<UserResult>, t: Throwable) {
                Log.i("Login:", "OnFailure ${t.message.toString()}")
                Toast.makeText(this@MainActivity, "Connection Timed Out! Try Again!", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<UserResult>, response: Response<UserResult>) {
                if(response.isSuccessful) {

                    admins = response.body()?.position ?: false
                    userid = response.body()?.userid ?: 1231234
                    ulatitude = response.body()?.ulatitude ?: 0.0
                    ulongitude = response.body()?.ulongitude ?: 0.0
                    username4D = response.body()?.username ?: ""
                    username = response.body()?.username ?: ""

                    Log.i("Login", "Success ${response.body()}")


                }
                else{
                    Log.i("Login", "Failure ${response.errorBody().toString()}")
                    Toast.makeText(this@MainActivity, "Invalid Login info!", Toast.LENGTH_LONG).show()

                }
            }

        })
    }
    fun getAllCampaigns(){
        val call: Call<MutableList<Campaign>> = ServiceBuilder.create().getAllCampaigns()

        call.enqueue(object: Callback<MutableList<Campaign>> {
            override fun onFailure(call: Call<MutableList<Campaign>>, t: Throwable) {
                Log.i("Campaigns ", "onFailure ${t.message.toString()}")
            }

            override fun onResponse(call: Call<MutableList<Campaign>>, response: Response<MutableList<Campaign>>) {
                if(response.isSuccessful){

                    val list = response.body()

                    campaign = list

                    vRecycle?.apply {
                        vRecycle.itemAnimator = DefaultItemAnimator()
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        (layoutManager as LinearLayoutManager).isSmoothScrollbarEnabled = true
                        (layoutManager as LinearLayoutManager).stackFromEnd = true
                        (layoutManager as LinearLayoutManager).supportsPredictiveItemAnimations()


                        adapter = RecyclerViewAdapter(campaign)


                    }
                }
                else{
                    Log.i("Properties ", "OnResponseFailure ${response.errorBody()}")
                }
            }

        })
    }


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {


//change fragment with each menu click

        when (p0.itemId) {

            R.id.navigation_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.navigation_map -> {
                val intent = Intent(this, MapsActivity::class.java)
                startActivity(intent)
            }
            R.id.navigation_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }

        }
        return true
    }

}
