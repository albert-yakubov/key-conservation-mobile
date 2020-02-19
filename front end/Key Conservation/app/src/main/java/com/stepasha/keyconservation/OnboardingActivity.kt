package com.stepasha.keyconservation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.stepasha.keyconservation.fragments.Board3Fragment
import com.stepasha.keyconservation.fragments.BoardTwoFragment
import kotlinx.android.synthetic.main.fragment_board_two.*

class OnboardingActivity : AppCompatActivity(){




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        loadFragment(BoardTwoFragment())

        button1.setOnClickListener {
            val newFragment = Board3Fragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_container, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
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
