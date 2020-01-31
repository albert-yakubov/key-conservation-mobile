package com.stepasha.keyconservation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.stepasha.keyconservation.adapter.RecyclerViewAdapter
import com.stepasha.keyconservation.model.Campaign
import com.stepasha.keyconservation.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    companion object{
        var campaign: MutableList<Campaign>? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(this)

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
                        layoutManager = LinearLayoutManager(this@MainActivity)
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
                val intent = Intent(this, MainActivity::class.java)
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
