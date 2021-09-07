package com.example.practicaactivities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.moshi.Moshi

private val PREFS = "MY_PREFERENCES"
private lateinit var preferences: SharedPreferences
private val moshi = Moshi.Builder().build()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        initView()
    }

    private lateinit var ivPicture: ImageView
    private lateinit var btnBack: Button
    private lateinit var btnNext: Button
    private lateinit var btnInfo: Button
    private var contador = 0
    private var contLimit = Picture.pictures.size

    private fun initView(){
        ivPicture = findViewById(R.id.ivPicture)
        btnBack = findViewById(R.id.btnBack)
        btnNext = findViewById(R.id.btnNext)
        btnInfo = findViewById(R.id.btnInfo)

        btnBack.setOnClickListener{
            lastImage()
        }
        btnNext.setOnClickListener{
            nextImage()
        }
        btnInfo.setOnClickListener{
            infoImage()
        }

        ivPicture.setImageResource(Picture.pictures[contador].source)
    }


    private fun lastImage(){
        contador--
        if(contador >= 0){
            ivPicture.setImageResource(Picture.pictures[contador].source)
        }else{
            contador = contLimit - 1
            ivPicture.setImageResource(Picture.pictures[contador].source)
        }
    }

    private fun nextImage(){
        contador++
        if(contador < contLimit){
            ivPicture.setImageResource(Picture.pictures[contador].source)
        }else{
            contador = 0
            ivPicture.setImageResource(Picture.pictures[contador].source)
        }
    }

    private fun infoImage(){
        startActivity(Intent(this, InfoActivity::class.java).apply {
            putExtra("selectedImage", Picture.pictures[contador])
        })
    }
}