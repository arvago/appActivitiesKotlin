package com.example.practicaactivities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class ImageFragment : Fragment(R.layout.fragment_image) {
    override fun onResume() {
        super.onResume()
        initView()
    }

    private lateinit var ivPictureD: ImageView
    private lateinit var picture: Picture

    private fun initView(){
        picture = requireArguments().getParcelable("imageSource") ?: Picture()
        ivPictureD = requireView().findViewById(R.id.ivPictureD)
        ivPictureD.setImageResource(picture.source)
    }
}