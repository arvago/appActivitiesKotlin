package com.example.practicaactivities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.squareup.moshi.Moshi

class MainActivity : AppCompatActivity() {

    lateinit var preferences: SharedPreferences
    val moshi = Moshi.Builder().build()
    val PREFS = "MY_PREFERENCES"
    val PIC_PREFERENCES = "PIC_PREFERENCES"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences = getSharedPreferences(PREFS, Context.MODE_PRIVATE)

        supportFragmentManager.beginTransaction().add(R.id.container, CarouselFragment()).commit()
    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right)
            replace(R.id.container, fragment)
            addToBackStack(fragment.tag)
            commit()
        }
    }
}