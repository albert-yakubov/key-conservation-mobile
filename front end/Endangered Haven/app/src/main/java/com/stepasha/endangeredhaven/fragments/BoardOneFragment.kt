package com.stepasha.endangeredhaven.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stepasha.endangeredhaven.R


class BoardOneFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val v =  inflater.inflate(R.layout.fragment_board_one, container, false)

        return v
    }





}



