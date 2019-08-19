package com.example.paddydtb.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.paddydtb.R
import com.example.paddydtb.Utils.WebService
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.fragment_identification.view.*


class Identification : Fragment() {

    var kProgressHUD: KProgressHUD? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_identification, container, false)

        kProgressHUD = KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel("Loading")
            .setCancellable(false)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)



        view.button_identify.setOnClickListener {
            activity?.runOnUiThread {
                if (kProgressHUD != null) {
                    if (!kProgressHUD!!.isShowing) {
                        kProgressHUD!!.show();
                    }
                }
                WebService.sendImage(context!!) { status, message, body ->

                    if (status) {
                        if (kProgressHUD != null) {
                            if (kProgressHUD!!.isShowing) {
                                kProgressHUD!!.dismiss()
                            }
                        }
                        val jsonData = body
                        val arguments = Bundle()
                        arguments.putString("BodyItems", body)
                        val tempFragment = Details()
                        tempFragment.arguments = arguments

                        fragmentManager!!.beginTransaction().replace(R.id.flContent, tempFragment).addToBackStack(null)
                            .commit()
                    }
                }
            }
        }
        return view
    }


}
