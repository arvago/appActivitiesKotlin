package com.example.practicaactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout
import android.widget.ImageView
import android.widget.TextView

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        setView()
    }

    private lateinit var ivPictureInfo: ImageView
    private lateinit var txvInfo: TextView
    private lateinit var ivFavorite: ImageView
    private lateinit var picture: Picture
    private var favFlag: Boolean = false

    private fun setView(){
        picture = intent.getParcelableExtra("selectedImage") ?: Picture()
        ivPictureInfo = findViewById(R.id.ivPictureInfo)
        txvInfo = findViewById(R.id.txvInfo)
        ivFavorite = findViewById(R.id.ivFavorite)

        ivPictureInfo.setImageResource(picture.source)
        txvInfo.text = picture.title + ": " + picture.description
        if(picture.favorite == false){
            ivFavorite.setImageResource(R.drawable.estrella)
        }else{
            ivFavorite.setImageResource(R.drawable.estrella_rellena)
        }

        ivPictureInfo.setOnClickListener{
            showImage()
        }
        ivFavorite.setOnClickListener {
            addToFavorites()
        }
    }

    private fun showImage(){
        startActivity(Intent(this, ImageActivity::class.java).apply {
            putExtra("imageSource", picture)
        })
    }

    private fun addToFavorites(){
        if(picture.favorite == false && !favFlag){
            ivFavorite.setImageResource(R.drawable.estrella_rellena)
            favFlag = true

            Picture.pictures.forEach {
                when(it.id) {
                    picture.id -> it.favorite = true
                }
            }
        }else{
            ivFavorite.setImageResource(R.drawable.estrella)
            favFlag = false

            Picture.pictures.forEach {
                when(it.id) {
                    picture.id -> it.favorite = false
                }
            }
        }
    }
}