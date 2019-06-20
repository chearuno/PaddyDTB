package com.example.paddydtb

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBarDrawerToggle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.example.paddydtb.Fragments.ChatAI
import com.example.paddydtb.Fragments.Details
import com.example.paddydtb.Fragments.Profile
import com.example.paddydtb.Fragments.FindNow
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    private var mtoggle: ActionBarDrawerToggle? = null
    private var mFragmentTransaction: FragmentTransaction? = null
    private var mFragmentManager: FragmentManager? = null
    private val BACK_STACK_ROOT_TAG = "root_fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Paddy DTB"

        mFragmentManager = supportFragmentManager
        mFragmentTransaction = mFragmentManager!!.beginTransaction()
        mFragmentTransaction!!.replace(R.id.flContent, FindNow())
        mFragmentTransaction!!.commit()

        setSupportActionBar(toolbar)

        mtoggle = setupDrawerToggle()
        maindrawer.addDrawerListener(mtoggle!!)

        val navigationView: NavigationView = findViewById(R.id.nvView)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            maindrawer.closeDrawers()

            var fragment: Fragment? = null

            // Handle navigation view item clicks here.
            when (menuItem.itemId) {

                R.id.find_screen -> {
                    fragment = FindNow()
                }
                R.id.details_screen -> {
                    fragment = Details()
                }
                R.id.chat_screen -> {
                    fragment = ChatAI()
                }
                R.id.profile_screen -> {
                    fragment = Profile()
                }
            }
            // Insert the fragment by replacing any existing fragment
            val fragmentManager = supportFragmentManager
            // fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment!!).commit()

            // Highlight the selected item has been done by NavigationView
            menuItem.isChecked = true
            // Set action bar title
            true
        }
    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return ActionBarDrawerToggle(this, maindrawer, toolbar, R.string.open, R.string.close)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mtoggle?.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Pass any configuration change to the drawer toggles
        mtoggle?.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (mtoggle!!.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
