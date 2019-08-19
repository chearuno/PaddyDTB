package com.example.paddydtb.Fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.paddydtb.R
import kotlinx.android.synthetic.main.fragment_findnow.*
import kotlinx.android.synthetic.main.fragment_findnow.view.*
import kotlinx.android.synthetic.main.fragment_findnow.view.imageView2
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class FindNow : Fragment() {
    private val TAG = "Camera"
    private val CAMERA_REQUEST = 1
    internal lateinit var mCurrentPhotoPath: String
    private val RESULT_LOAD_IMAGE = 1
    private var filePath: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(com.example.paddydtb.R.layout.fragment_findnow, container, false)

        view.button_capure_now.setOnClickListener {
            dispatchTakePictureIntent()
        }

        view.button_select.setOnClickListener {
            val i = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )

            startActivityForResult(i, RESULT_LOAD_IMAGE)
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK
            && data != null && data.data != null
        ) {
            filePath = data.data
            val stringUri: String
            stringUri = filePath.toString()
            try {

                val arguments = Bundle()
                arguments.putString("FilePath", stringUri)

                val tempFragment = Identification()
                tempFragment.arguments = arguments

                fragmentManager!!.beginTransaction().replace(R.id.flContent, tempFragment).addToBackStack(null).commit()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            try {

                val bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, Uri.parse(mCurrentPhotoPath))
                //image_main.setImageBitmap(bitmap)

                val arguments = Bundle()
                arguments.putString("FilePath", mCurrentPhotoPath)

                val tempFragment = Identification()
                tempFragment.arguments = arguments

                fragmentManager!!.beginTransaction().replace(R.id.flContent, tempFragment).addToBackStack(null).commit()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

    }


    private fun dispatchTakePictureIntent() {

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (cameraIntent.resolveActivity(activity!!.packageManager) != null) {
            // Create the File where the photo should go
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
                // Error occurred while creating the File
                Log.i(TAG, "IOException")
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile))
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            }
        }

    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES
        )
        val image = File.createTempFile(
            imageFileName, // prefix
            ".jpg", // suffix
            storageDir      // directory
        )

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.absolutePath
        return image
    }

}
