package com.example.practicaactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        initView()
    }

    private lateinit var ivPictureD: ImageView
    private lateinit var picture: Picture

    private fun initView(){
        picture = intent.getParcelableExtra("imageSource") ?: Picture()
        ivPictureD = findViewById(R.id.ivPictureD)
        ivPictureD.setImageResource(picture.source)
    }
}