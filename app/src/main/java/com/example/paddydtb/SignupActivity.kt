package com.example.paddydtb

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance()

        btn_reset_password.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@SignupActivity,
                    ResetPasswordActivity::class.java
                )
            )
        })

        sign_up_button.setOnClickListener(View.OnClickListener {
            // finish();
            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
        })

        sign_in_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {

                val email = signup_email.getText().toString().trim { it <= ' ' }
                val password = signup_password.getText().toString().trim { it <= ' ' }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(applicationContext, "Enter email address!", Toast.LENGTH_SHORT).show()
                    return
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()
                    return
                }

                if (password.length < 6) {
                    Toast.makeText(
                        applicationContext,
                        "Password too short, enter minimum 6 characters!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }

                progressBar_singup.setVisibility(View.VISIBLE)
                //create user
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(
                        this@SignupActivity
                    ) { task ->
                        Toast.makeText(
                            this@SignupActivity,
                            "createUserWithEmail:onComplete:" + task.isSuccessful,
                            Toast.LENGTH_SHORT
                        ).show()
                        progressBar_singup.setVisibility(View.GONE)
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful) {
                            Toast.makeText(
                                this@SignupActivity, "Authentication failed." + task.exception!!,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            startActivity(Intent(this@SignupActivity, MainActivity::class.java))
                            finish()
                        }
                    }
            }
        })
    }


    fun isValidEmail(target: CharSequence?): Boolean {
        return if (target == null) false else android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()

    }

    override fun onResume() {
        super.onResume()
        progressBar_singup.setVisibility(View.GONE)
    }
}

