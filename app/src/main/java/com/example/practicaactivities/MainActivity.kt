package com.example.practicaactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private lateinit var ivPicture: ImageView
    private lateinit var btnBack: Button
    private lateinit var btnNext: Button
    private lateinit var btnInfo: Button
    private lateinit var tvFav: TextView
    private var contadorCarousel = 0
    private var contadorNoFavs = 0
    private var contLimit = Picture.pictures.size

    private fun initView(){
        ivPicture = findViewById(R.id.ivPicture)
        btnBack = findViewById(R.id.btnBack)
        btnNext = findViewById(R.id.btnNext)
        btnInfo = findViewById(R.id.btnInfo)
        tvFav = findViewById(R.id.tvFav)

        btnBack.setOnClickListener{
            lastImage()
        }
        btnNext.setOnClickListener{
            nextImage()
        }
        btnInfo.setOnClickListener{
            infoImage()
        }

        ivPicture.setImageResource(Picture.pictures[contadorCarousel].source)
    }

    private fun lastImage(){
        contadorCarousel--
        if(contadorCarousel >= 0){
            ivPicture.setImageResource(Picture.pictures[contadorCarousel].source)
        }else{
            contadorCarousel = contLimit - 1
            ivPicture.setImageResource(Picture.pictures[contadorCarousel].source)
        }
    }

    private fun nextImage(){
        contadorCarousel++
        if(contadorCarousel < contLimit){
            ivPicture.setImageResource(Picture.pictures[contadorCarousel].source)
        }else{
            contadorCarousel = 0
            ivPicture.setImageResource(Picture.pictures[contadorCarousel].source)
        }
    }

    private fun infoImage(){
        startActivity(Intent(this, InfoActivity::class.java).apply {
            putExtra("selectedImage", Picture.pictures[contadorCarousel])
        })
    }
}