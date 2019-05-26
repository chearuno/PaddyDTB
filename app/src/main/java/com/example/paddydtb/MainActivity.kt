package com.example.paddydtb

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.widget.ProgressBar
import com.google.firebase.auth.FirebaseAuth
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import com.example.paddydtb.Fragments.ChatAI
import com.example.paddydtb.Fragments.Details
import com.example.paddydtb.Fragments.Profile
import com.example.paddydtb.Fragments.FindNow
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private var mDrawerlayout: DrawerLayout? = null
    private var mtoggle: ActionBarDrawerToggle? = null
    private var mToolbar: Toolbar? = null
    private var nvDrawer: NavigationView? = null

    private val authListener: FirebaseAuth.AuthStateListener? = null
    private var auth: FirebaseAuth? = null
    private val progressBar: ProgressBar? = null
    private var mFragmentTransaction: FragmentTransaction? = null
    private var mFragmentManager: FragmentManager? = null
    private val BACK_STACK_ROOT_TAG = "root_fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Paddy DTB"
        //get firebase auth instance
        auth = FirebaseAuth.getInstance()
        //get current user
        val user = FirebaseAuth.getInstance().currentUser

        mFragmentManager = supportFragmentManager
        mFragmentTransaction = mFragmentManager!!.beginTransaction()
        mFragmentTransaction!!.replace(R.id.flContent, FindNow())
        mFragmentTransaction!!.commit()

        mToolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(mToolbar)

//        nvDrawer = findViewById(R.id.nvView) as NavigationView
//        setupDrawerContent(nvDrawer!!)


        mDrawerlayout = findViewById(R.id.maindrawer)
        mtoggle = setupDrawerToggle()
        mDrawerlayout?.addDrawerListener(mtoggle!!)


//        super.onCreate(savedInstanceState);
//        //setContentView(R.layout.home_screen_activity);
//        Button button = (Button) findViewById(R.id.signout);
//        mAuth = FirebaseAuth.getInstance();
//        mAuthListner = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if (firebaseAuth.getCurrentUser()==null)
//                {
//                    startActivity(new Intent(Home_screen.this, singin_activity.class));
//                }
//            }
//        };
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAuth.signOut();
//            }
//        });
    }


    private fun setupDrawerToggle(): ActionBarDrawerToggle {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return ActionBarDrawerToggle(this, mDrawerlayout, mToolbar, R.string.open, R.string.close)
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

/*
    public boolean onOptionsItemSelected(MenuItem item){
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerlayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
override fun onNavigationItemSelected(item: MenuItem): Boolean {
    // Handle navigation view item clicks here.
    loadView(item.itemId)

    maindrawer.closeDrawer(GravityCompat.START)
    return true
}

    private fun loadView(id: Int) {

        val fragment = when (id) {
            R.id.find_screen -> {
                FindNow()
            }
            R.id.details_screen -> {
                Details()
            }
            R.id.chat_screen -> {
                ChatAI()
            }
            R.id.profile_screen -> {
                Profile()
            }

            else -> {
                FindNow()
            }
        }

        supportFragmentManager.beginTransaction().replace(R.id.relativeLayout, fragment).addToBackStack(null).commit()
    }
/*
    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            selectDrawerItem(menuItem)
            true
        }


    }

    private fun selectDrawerItem(String: menuItem)
    {
        var fragment: Fragment? = null
        var fragmentClass: Class<*>? = null
//        when (menuItem.getItemId()) {
//            R.id.browse -> fragmentClass = Browse::class.java
//            R.id.addupload -> fragmentClass = Add::class.java
//            R.id.myarts -> fragmentClass = MyArts::class.java
//            R.id.profile -> fragmentClass = Profile::class.java
//            R.id.catogery -> fragmentClass = Category::class.java
//
//
//            else -> fragmentClass = Browse::class.java
//        }

        try {
            fragment = fragmentClass!!.newInstance() as Fragment
        } catch (e: Exception) {
            e.printStackTrace()
        }


        // Insert the fragment by replacing any existing fragment
        val fragmentManager = supportFragmentManager
        // fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment!!).addToBackStack(null).commit()

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true)
        // Set action bar title
        setTitle(menuItem.getTitle())
        // Close the navigation drawer
        mDrawerlayout!!.closeDrawers()

    }
*/
}
