package com.rockspin.uktsdprep.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.rockspin.uktsdprep.R
import com.rockspin.uktsdprep.afterMeasured
import com.rockspin.uktsdprep.ui.terminology.TerminologyFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val terminologyFragment: TerminologyFragment by lazy {
        TerminologyFragment.newInstance()
    }

    private var activeFragment: Fragment? = null

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                selectFragment(terminologyFragment)
            }
            R.id.navigation_dashboard -> {

            }
            R.id.navigation_notifications -> {

            }
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        selectFragment(terminologyFragment)

        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        bottomNavigation.afterMeasured {
            val layoutParams = fragmentHolder.layoutParams as CoordinatorLayout.LayoutParams
            layoutParams.setMargins(0, 0, 0, height)

            fragmentHolder.layoutParams = layoutParams
        }
    }

    private fun selectFragment(fragment: Fragment) {
        if (activeFragment != fragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, fragment)
                .commit()

            activeFragment = fragment
        }
    }
}
