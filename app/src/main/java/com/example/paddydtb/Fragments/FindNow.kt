package com.example.paddydtb.Fragments

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.paddydtb.BuildConfig
import com.example.paddydtb.R
import com.example.paddydtb.CameraActivity
import kotlinx.android.synthetic.main.fragment_findnow.view.*
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
    private var photoFile: File? = null
    var completedProcessImagePath = ""

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

        view.button_identify.setOnClickListener {
            val i = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )

            startActivityForResult(i, RESULT_LOAD_IMAGE)
        }
        view.button_live.setOnClickListener {
            val intent = Intent(context, CameraActivity::class.java)
            startActivity(intent)

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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 0) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                dispatchTakePictureIntent()
            }
        }

    }

    private fun dispatchTakePictureIntent() {
        if (ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                0
            )
            //Toast.makeText(activity, "Please allow camera access to take the photo", Toast.LENGTH_LONG).show()
//            Util.alertDialog("Alert!","Please allow camera access to take the photo",context)
        } else {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (cameraIntent.resolveActivity(activity!!.packageManager) != null) {
                try {
                    photoFile = createImageFile()
                    completedProcessImagePath = photoFile!!.getAbsolutePath()
                    // Continue only if the File was successfully createdx
                    val authority = BuildConfig.APPLICATION_ID + ".provider"
                    if (photoFile != null) {
                        var photoURI = FileProvider.getUriForFile(
                            context!!,
                            authority,
                            photoFile!!
                        )
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                        startActivityForResult(cameraIntent, CAMERA_REQUEST)
                    }
                } catch (ex: Exception) {
                    // Error occurred while creating the File
                    displayMessage(context!!, "Capture Image Bug: " + ex.message.toString())
                    Log.e("Capture Image Bug:", ex.message.toString())
                }
            }
        }
    }

    private fun displayMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
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
