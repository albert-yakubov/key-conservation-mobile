package com.stepasha.keyconservation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.stepasha.keyconservation.Util.AppController
import com.stepasha.keyconservation.adapter.RecyclerViewAdapter
import com.stepasha.keyconservation.model.Campaign
import com.stepasha.keyconservation.retrofit.LoginServiceSql
import com.stepasha.keyconservation.retrofit.ServiceBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

        if (!LoginActivity.admins){
            view_floatingbutton.visibility = View.GONE
        }else if(LoginActivity.admins){
            view_floatingbutton.visibility = View.VISIBLE
        }

        view_floatingbutton.setOnClickListener {
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        }

        getAllCampaigns()


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
