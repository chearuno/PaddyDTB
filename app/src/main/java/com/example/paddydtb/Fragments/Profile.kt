package com.example.paddydtb.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.paddydtb.LoginActivity
import com.example.paddydtb.R
import com.google.firebase.auth.FirebaseAuth
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.fragment_profile.view.*

class Profile : Fragment() {
    lateinit var mAuthListner: FirebaseAuth.AuthStateListener
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)

        //get firebase auth instance
        mAuth = FirebaseAuth.getInstance()
        //get current user
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            view.textView_profile_name.setText(user.displayName)
            view.textView_profile_email.setText(user.email)
        }
        mAuthListner = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user == null) {
                alertsignout()
            }
        }
        view.signout_button11.setOnClickListener(View.OnClickListener { signOut() })
        return view
    }


    //sign out method
    fun signOut() {
        mAuth.signOut()
    }

    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(mAuthListner)
    }

    override fun onStop() {
        super.onStop()
        if (mAuthListner != null) {
            mAuth.removeAuthStateListener(mAuthListner)
        }
    }

    fun alertsignout() {
        Alerter.create(activity)
            .setTitle("Confirm SignOut")
            .setText("Are you sure you want to Signout?")
            .enableSwipeToDismiss()
            .setBackgroundColorRes(R.color.colorAccent)
            .addButton("Yes", R.style.AlertButton, View.OnClickListener {
                val i = Intent(
                    activity,
                    LoginActivity::class.java
                )
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)
            })
            .addButton("No", R.style.AlertButton, View.OnClickListener {
                Alerter.hide()
            })
            .show()
    }
}
