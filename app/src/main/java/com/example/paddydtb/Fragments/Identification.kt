package com.example.paddydtb.Fragments

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.paddydtb.R
import com.example.paddydtb.Utils.WebService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.kaopiz.kprogresshud.KProgressHUD
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_identification.view.*
import java.io.IOException
import java.util.*


class Identification : Fragment() {

    var kProgressHUD: KProgressHUD? = null
    internal lateinit var db: FirebaseFirestore
    internal lateinit var storage: FirebaseStorage
    internal lateinit var storageReference: StorageReference
    var imgPath = ""
    lateinit var uri: Uri
    private var selection = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_identification, container, false)

        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference

        kProgressHUD = KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel("Detecting")
            .setDetailsLabel("Please Wait...")
            .setCancellable(false)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)

        imgPath = arguments!!.getString("FilePath")
        uri = Uri.parse(imgPath)

        try {

//            val bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, uri)
//            view.imageDitected.setImageBitmap(bitmap)
            Picasso.get().load(uri).fit().centerInside().into(view.imageDetection)

        } catch (e: IOException) {
            e.printStackTrace()
        }

        view.imageDetection.setOnClickListener {
            val fm = activity!!.getSupportFragmentManager()
            val bundle = Bundle()
            bundle.putString("imageURI", imgPath)
            val addFragment = FullScreen()
            addFragment.arguments = bundle
            fm.beginTransaction().replace(R.id.flContent, addFragment).addToBackStack(null).commit()

        }


        view.button_identify.setOnClickListener {

            val items = arrayOf<CharSequence>(
                "Detected as Pest",
                "Detected as Weed",
                "Detected as Disease",
                "Detected as Deficiency"
            )


            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Select Detection Methord")

            builder.setItems(items) { dialog, item ->
                selectType(items[item].toString())

                dialog.dismiss()
            }
            builder.show()


        }


        return view
    }

    private fun selectType(seleced: String) {
        val progressDialog = ProgressDialog(activity)
        progressDialog.setTitle("Uploading...")
        progressDialog.show()
        progressDialog.setCancelable(false)
        val imageId = UUID.randomUUID().toString()
        val imagename = imageId + ".jpg"

        val ref = storageReference.child("images/$imagename")
        if (seleced == "Detected as Pest") {


            ref.putFile(uri).addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener { uri ->
                    Log.e(
                        "URL",
                        "onSuccess: uri= $uri"
                    )
                    progressDialog.dismiss()
                    Toast.makeText(activity, "Image Uploaded", Toast.LENGTH_SHORT).show()
                    detectImage(uri.toString())
                }
            }
                .addOnFailureListener { e ->
                    progressDialog.dismiss()
                    Toast.makeText(activity, "Image Upload Failed " + e.message, Toast.LENGTH_SHORT)
                        .show()
                }
                .addOnProgressListener { taskSnapshot ->
                    val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot
                        .totalByteCount
                    progressDialog.setMessage("Uploading..  " + progress.toInt() + "%")
                }
        } else if (seleced == "Detected as Disease") {
            ref.putFile(uri).addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener { uri ->
                    Log.e(
                        "URL",
                        "onSuccess: uri= $uri"
                    )
                    progressDialog.dismiss()
                    Toast.makeText(activity, "Image Uploaded", Toast.LENGTH_SHORT).show()
                    detectdiseaseImage(uri.toString())
                }
            }
                .addOnFailureListener { e ->
                    progressDialog.dismiss()
                    Toast.makeText(activity, "Image Upload Failed " + e.message, Toast.LENGTH_SHORT)
                        .show()
                }
                .addOnProgressListener { taskSnapshot ->
                    val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot
                        .totalByteCount
                    progressDialog.setMessage("Uploading..  " + progress.toInt() + "%")
                }
        } else if (seleced == "Detected as Weed") {


            ref.putFile(uri).addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener { uri ->
                    Log.e(
                        "URL",
                        "onSuccess: uri= $uri"
                    )
                    progressDialog.dismiss()
                    Toast.makeText(activity, "Image Uploaded", Toast.LENGTH_SHORT).show()
                    detectWeedImage(uri.toString())
                }
            }
                .addOnFailureListener { e ->
                    progressDialog.dismiss()
                    Toast.makeText(activity, "Image Upload Failed " + e.message, Toast.LENGTH_SHORT)
                        .show()
                }
                .addOnProgressListener { taskSnapshot ->
                    val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot
                        .totalByteCount
                    progressDialog.setMessage("Uploading..  " + progress.toInt() + "%")
                }
        } else if (seleced == "Detected as Deficiency") {
            ref.putFile(uri).addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener { uri ->
                    Log.e(
                        "URL",
                        "onSuccess: uri= $uri"
                    )
                    progressDialog.dismiss()
                    Toast.makeText(activity, "Image Uploaded", Toast.LENGTH_SHORT).show()
                    detectDeficiencyImage(uri.toString())
                }
            }
                .addOnFailureListener { e ->
                    progressDialog.dismiss()
                    Toast.makeText(activity, "Image Upload Failed " + e.message, Toast.LENGTH_SHORT)
                        .show()
                }
                .addOnProgressListener { taskSnapshot ->
                    val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot
                        .totalByteCount
                    progressDialog.setMessage("Uploading..  " + progress.toInt() + "%")
                }
        }

    }

    private fun detectImage(urlImage: String) {
        activity?.runOnUiThread {
            if (kProgressHUD != null) {
                if (!kProgressHUD!!.isShowing) {
                    kProgressHUD!!.setLabel("Detecting Pest")
                    kProgressHUD!!.show();
                }
            }
            WebService.sendImage(context!!, urlImage) { status, message, body ->

                if (status) {
                    if (kProgressHUD != null) {
                        if (kProgressHUD!!.isShowing) {
                            kProgressHUD!!.dismiss()
                        }
                    }
                    // val jsonData = body
                    val arguments = Bundle()
                    arguments.putString("BodyItems", body)
                    arguments.putBoolean("FromPest", true)
                    arguments.putString("imageURI", imgPath)
                    val tempFragment = Details()
                    tempFragment.arguments = arguments

                    fragmentManager!!.beginTransaction().replace(R.id.flContent, tempFragment)
                        .addToBackStack(null)
                        .commit()
                } else {
                    if (kProgressHUD != null) {
                        if (kProgressHUD!!.isShowing) {
                            kProgressHUD!!.dismiss()
                        }
                    }

                    activity!!.runOnUiThread(Runnable {
                        Toast.makeText(
                            activity,
                            "Something Went Wrong. Please Try again",
                            Toast.LENGTH_SHORT
                        ).show()
                    })

                }

            }
        }
    }


    private fun detectdiseaseImage(urlImage: String) {
        activity?.runOnUiThread {
            if (kProgressHUD != null) {
                if (!kProgressHUD!!.isShowing) {
                    kProgressHUD!!.setLabel("Detecting Disease")
                    kProgressHUD!!.show();
                }
            }
            WebService.sendDiseaseImage(context!!, urlImage) { status, message, body ->

                if (status) {
                    if (kProgressHUD != null) {
                        if (kProgressHUD!!.isShowing) {
                            kProgressHUD!!.dismiss()
                        }
                    }
                    // val jsonData = body
                    val arguments = Bundle()
                    arguments.putString("BodyItems", body)
                    arguments.putBoolean("FromPest", false)
                    arguments.putString("imageURI", imgPath)
                    val tempFragment = Details()
                    tempFragment.arguments = arguments

                    fragmentManager!!.beginTransaction().replace(R.id.flContent, tempFragment)
                        .addToBackStack(null)
                        .commit()
                } else {
                    if (kProgressHUD != null) {
                        if (kProgressHUD!!.isShowing) {
                            kProgressHUD!!.dismiss()
                        }
                    }

                    activity!!.runOnUiThread(Runnable {
                        Toast.makeText(
                            activity,
                            "Something Went Wrong. Please Try again",
                            Toast.LENGTH_SHORT
                        ).show()
                    })

                }

            }
        }
    }

    private fun detectWeedImage(urlImage: String) {
        activity?.runOnUiThread {
            if (kProgressHUD != null) {
                if (!kProgressHUD!!.isShowing) {
                    kProgressHUD!!.setLabel("Detecting Weed")
                    kProgressHUD!!.show();
                }
            }
            WebService.sendWeedImage(context!!, urlImage) { status, message, body ->

                if (status) {
                    if (kProgressHUD != null) {
                        if (kProgressHUD!!.isShowing) {
                            kProgressHUD!!.dismiss()
                        }
                    }
                    // val jsonData = body
                    val arguments = Bundle()
                    arguments.putString("BodyItems", body)
                    arguments.putBoolean("FromPest", false)
                    arguments.putString("imageURI", imgPath)
                    val tempFragment = Details()
                    tempFragment.arguments = arguments

                    fragmentManager!!.beginTransaction().replace(R.id.flContent, tempFragment)
                        .addToBackStack(null)
                        .commit()
                } else {
                    if (kProgressHUD != null) {
                        if (kProgressHUD!!.isShowing) {
                            kProgressHUD!!.dismiss()
                        }
                    }

                    activity!!.runOnUiThread(Runnable {
                        Toast.makeText(
                            activity,
                            "Something Went Wrong. Please Try again",
                            Toast.LENGTH_SHORT
                        ).show()
                    })

                }

            }
        }
    }

    private fun detectDeficiencyImage(urlImage: String) {
        activity?.runOnUiThread {
            if (kProgressHUD != null) {
                if (!kProgressHUD!!.isShowing) {
                    kProgressHUD!!.setLabel("Detecting Deficiency")
                    kProgressHUD!!.show();
                }
            }
            WebService.sendDificiencyImage(context!!, urlImage) { status, message, body ->

                if (status) {
                    if (kProgressHUD != null) {
                        if (kProgressHUD!!.isShowing) {
                            kProgressHUD!!.dismiss()
                        }
                    }
                    // val jsonData = body
                    val arguments = Bundle()
                    arguments.putString("BodyItems", body)
                    arguments.putBoolean("FromPest", false)
                    arguments.putString("imageURI", imgPath)
                    val tempFragment = Details()
                    tempFragment.arguments = arguments

                    fragmentManager!!.beginTransaction().replace(R.id.flContent, tempFragment)
                        .addToBackStack(null)
                        .commit()
                } else {
                    if (kProgressHUD != null) {
                        if (kProgressHUD!!.isShowing) {
                            kProgressHUD!!.dismiss()
                        }
                    }

                    activity!!.runOnUiThread(Runnable {
                        Toast.makeText(
                            activity,
                            "Something Went Wrong. Please Try again",
                            Toast.LENGTH_SHORT
                        ).show()
                    })

                }

            }
        }
    }

}
