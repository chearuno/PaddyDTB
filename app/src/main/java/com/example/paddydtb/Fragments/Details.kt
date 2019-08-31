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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_details, container, false)

        if (arguments!!.getString("BodyItems") != null) {
            val bodyItem:String = arguments!!.getString("BodyItems")!!

//            val toBeSearched = "<title>"
//            val ix = bodyItem.IndexOf(toBeSearched)
//
//            if (ix != -1) {
//                val code = myString.Substring(ix + toBeSearched.Length)
//                // do something here
//            }

            if (arguments!!.getBoolean("FromPest") != null) {
                Picasso.get().load("http://192.168.1.103:5000/static/tfOutput.jpg").fit()
                    .centerInside().error(R.mipmap.mainlogo).into(view.imageDetected)
                Log.e("From","Pest")
            }else{
                Picasso.get().load(R.mipmap.mainlogo).fit()
                    .centerInside().error(R.mipmap.mainlogo).into(view.imageDetected)
                Log.e("From","Other")
            }
            view.myWebView.loadData(bodyItem, "text/html", "UTF-8");
        }

        view.imageDetected.setOnClickListener {
            val fm = activity!!.getSupportFragmentManager()
            val bundle = Bundle()
            bundle.putString("imageURI", "http://192.168.1.103:5000/static/tfOutput.jpg")
            val addFragment = FullScreen()
            addFragment.arguments = bundle
            fm.beginTransaction().replace(R.id.flContent, addFragment).addToBackStack(null).commit()

        }

        return view
    }


}
