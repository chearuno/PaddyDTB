package com.example.paddydtb.Fragments

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.paddydtb.R
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
            val bodyItem = arguments!!.getString("BodyItems")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.detailsBody.setText(
                    Html.fromHtml(
                        bodyItem,
                        Html.FROM_HTML_MODE_COMPACT
                    )
                );
            } else {
                view.detailsBody.setText(Html.fromHtml(bodyItem));
            }
        }

        return view
    }


}
