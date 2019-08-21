package com.example.paddydtb.Fragments

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler
import com.example.paddydtb.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_fullscreen.view.*

class FullScreen : Fragment() {

    internal var imageUri: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_fullscreen, container, false)

        imageUri = arguments!!.getString("imageURI")
        Log.d("111", imageUri)


        var uri: Uri
        uri = Uri.parse(imageUri)

        Picasso.get()
            .load(uri)
            .into(view.imageViewFullScreen)

        view.imageViewFullScreen.setOnTouchListener( ImageMatrixTouchHandler(view.getContext()));

        return view
    }


}
