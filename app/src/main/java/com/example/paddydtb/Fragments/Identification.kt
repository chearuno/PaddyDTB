package com.example.paddydtb.Fragments

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.provider.MediaStore
import kotlinx.android.synthetic.main.fragment_identification.view.imageViewtestsss
import java.io.IOException


class Identification : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(com.example.paddydtb.R.layout.fragment_identification, container, false)
        // Inflate the layout for this fragment
        val imgPath = arguments!!.getString("FilePath")
        val uri: Uri
        val stringUri: String
        uri = Uri.parse(imgPath)

//        val bitmap = BitmapFactory.decodeFile(imgPath)
//        view.imageView2.setImageBitmap(bitmap)


        try {

            val bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, uri)
            //image_main.setImageBitmap(bitmap)
            view.imageViewtestsss.setImageBitmap(bitmap)


        } catch (e: IOException) {
            e.printStackTrace()
        }

//        view.button_capture?.setOnClickListener {
//        }
        return view
    }

}
