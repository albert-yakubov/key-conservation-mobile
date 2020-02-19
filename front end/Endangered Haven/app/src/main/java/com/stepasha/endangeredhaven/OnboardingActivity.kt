package com.stepasha.endangeredhaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.stepasha.endangeredhaven.fragments.*
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity(){




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        loadFragment(BoardOneFragment())
        button2.visibility = View.INVISIBLE
        button3.visibility = View.INVISIBLE
        button4.visibility = View.INVISIBLE
        button5.visibility = View.INVISIBLE
        button6.visibility = View.INVISIBLE

        button1.setOnClickListener {
            button2.visibility = View.VISIBLE
            button1.visibility = View.GONE
         val newFragment : Fragment = Board2Fragment()
         val transaction = supportFragmentManager.beginTransaction()
         transaction.replace(R.id.fl_container, newFragment)
         transaction.addToBackStack(null)
         transaction.commit()
     }
        button2.setOnClickListener {
            button2.visibility = View.GONE
            button1.visibility = View.GONE
            button3.visibility = View.VISIBLE
            val newFragment : Fragment = Board3Fragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_container, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        button3.setOnClickListener {
            button2.visibility = View.GONE
            button1.visibility = View.GONE
            button3.visibility = View.GONE
            button4.visibility = View.VISIBLE
            val newFragment : Fragment = Board4Fragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_container, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        button4.setOnClickListener {
            button2.visibility = View.GONE
            button1.visibility = View.GONE
            button3.visibility = View.GONE
            button4.visibility = View.GONE
            button5.visibility = View.VISIBLE
            val newFragment : Fragment = Board5Fragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_container, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        button5.setOnClickListener {
            button2.visibility = View.GONE
            button1.visibility = View.GONE
            button3.visibility = View.GONE
            button4.visibility = View.GONE
            button5.visibility = View.GONE
            button6.visibility = View.VISIBLE
            val newFragment : Fragment = Board6Fragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_container, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        button6.setOnClickListener {
            val intent = Intent(this, ConservationRegisterActivity::class.java)
            startActivity(intent)
        }






    }
    private fun loadFragment(fragment: Fragment):Boolean{

        if(fragment != null) {

            supportFragmentManager.beginTransaction().replace(R.id.fl_container, fragment).commit()

            return true

        }

        return false
    }


}
