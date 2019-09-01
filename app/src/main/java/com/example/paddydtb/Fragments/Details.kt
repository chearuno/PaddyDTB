package com.example.paddydtb.Fragments


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


class Details : Fragment() {

    var code = ""
    var bodyItem =""
    var strtext = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_details, container, false)


             bodyItem = arguments!!.getString("BodyItems1")
             strtext = arguments!!.getBoolean("FromPest1")
            Log.e("StringB", bodyItem)
        Log.e("StringC", strtext.toString())


            if (bodyItem != "" && bodyItem != null) {
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
                Picasso.get().load("http://192.168.8.100:5000/static/tfOutput.jpg").fit()
                    .centerInside().error(R.mipmap.mainlogo).into(view.imageDetected)
                Log.e("From", "Pest")
            } else {
                view.imageDetected.visibility = View.GONE
//                Picasso.get().load(R.mipmap.mainlogo).fit()
//                    .centerInside().error(R.mipmap.mainlogo).into(view.imageDetected)
//                Log.e("From","Other")
            }
        }

        view.imageDetected.setOnClickListener {
            val fm = activity!!.getSupportFragmentManager()
            val bundle = Bundle()
            bundle.putString("imageURI", "http://192.168.8.102:5000/static/tfOutput.jpg")
            val addFragment = FullScreen()
            addFragment.arguments = bundle
            fm.beginTransaction().replace(R.id.flContent, addFragment).addToBackStack(null).commit()

        }

        view.button_more_details.setOnClickListener {
            val fm = activity!!.getSupportFragmentManager()
            val bundle = Bundle()
            bundle.putString("NameDetectedImage", code)
            val addFragment = MoreDtails()
            addFragment.arguments = bundle
            fm.beginTransaction().replace(R.id.flContent, addFragment).addToBackStack(null).commit()

        }
        return view
    }


}
