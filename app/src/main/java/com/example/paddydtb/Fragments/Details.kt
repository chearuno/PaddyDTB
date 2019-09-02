package com.example.paddydtb.Fragments


import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.paddydtb.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.view.*
import java.net.URL


class Details : Fragment() {

    var code = ""
    var bodyItem = ""
    var strtext = false
    var imagepath = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_details, container, false)


        bodyItem = arguments!!.getString("BodyItems")
        strtext = arguments!!.getBoolean("FromPest")


        Log.e("From", "Pest")
        if (bodyItem != "") {
            val ix = bodyItem.indexOf("<title>") + 7
            val xx = bodyItem.indexOf("</title>")


            for (i in ix..(xx - 1)) {
                code = code + bodyItem.get(i).toString()
            }
            Log.e("String", code)

            if (code != "") {

                view.result_page_title.text = "Detected - " + code
            }
            view.myWebView.loadData(bodyItem, "text/html", "UTF-8")

            if (arguments!!.getBoolean("FromPest")) {
                //                var uri: Uri
//                uri = Uri.parse("http://192.168.1.103:5000/static/tfOutput.jpg")
//                val url = URL("http://192.168.1.103:5000/static/tfOutput.jpg")
//                val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                Picasso.get().load("http://192.168.1.103:5000/static/tfOutput.jpg").fit()
                    .centerInside().error(R.mipmap.mainlogo).into(view.imageDetected)
//                view.imageDetected.setImageBitmap(bmp)
            } else {
               // view.imageDetected.visibility = View.GONE
                imagepath = arguments!!.getString("imageURI")
                var uri: Uri
                uri = Uri.parse(imagepath)
                Picasso.get().load(uri).fit()
                    .centerInside().error(R.mipmap.mainlogo).into(view.imageDetected)
            }
        }

        view.imageDetected.setOnClickListener {
            if (arguments!!.getBoolean("FromPest")) {
                val fm = activity!!.getSupportFragmentManager()
                val bundle = Bundle()
                bundle.putString("imageURI", "http://192.168.1.103:5000/static/tfOutput.jpg")
                val addFragment = FullScreen()
                addFragment.arguments = bundle
                fm.beginTransaction().replace(R.id.flContent, addFragment).addToBackStack(null)
                    .commit()
                code = ""
            }
        }
        if (code == "") {
            view.button_more_details.visibility = View.GONE
        }
        view.button_more_details.setOnClickListener {
            val fm = activity!!.getSupportFragmentManager()
            val bundle = Bundle()
            bundle.putString("NameDetectedImage", code)
            val addFragment = MoreDtails()
            addFragment.arguments = bundle
            fm.beginTransaction().replace(R.id.flContent, addFragment).addToBackStack(null).commit()
            code = ""
        }
        return view
    }


}
