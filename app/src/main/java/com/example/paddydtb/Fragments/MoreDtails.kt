package com.example.paddydtb.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.paddydtb.R
import kotlinx.android.synthetic.main.fragment_more_dtails.view.*


class MoreDtails : Fragment() {

var nameDetect = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_more_dtails, container, false)

        nameDetect = arguments!!.getString("NameDetectedImage")

        view.txt_more_detail_heading.text = nameDetect


        return view
    }
}


