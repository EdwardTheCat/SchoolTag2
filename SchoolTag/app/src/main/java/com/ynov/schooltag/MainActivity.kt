package com.ynov.schooltag

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.ynov.schooltag.delay.Delay
import com.ynov.schooltag.delay.DelaysFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var delaysfrag : DelaysFragment = DelaysFragment.newInstance()
    var activeFrag : Fragment?= null

    // creer une variable pour setter le fragment actif.
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_badger -> {
                // message.setText(R.string.title_badger)
                //    replaceFragment(testfrag)
                detachFragment(activeFrag)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_schedule -> {
                // message.setText(R.string.title_schedule)
                detachFragment(activeFrag)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_delays -> {
                //  message.setText(R.string.title_delays)
                // replaceFragment(delaysfrag)

                //detachFragment(activeFrag)
                replaceFragment(delaysfrag)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    fun detachFragment(frag: Fragment?){
        if(frag!=null) supportFragmentManager.beginTransaction().detach(frag).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    fun replaceFragment(frag: Fragment) {
        activeFrag = frag
        supportFragmentManager.beginTransaction()
            .attach(frag).commit()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, frag)
            .commit()

    }
}
