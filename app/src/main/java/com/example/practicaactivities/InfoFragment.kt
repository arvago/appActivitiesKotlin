package com.example.practicaactivities

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class InfoFragment : Fragment(R.layout.fragment_info) {
    override fun onResume() {
        super.onResume()
        setView()
    }

    private lateinit var ivPictureInfo: ImageView
    private lateinit var txvInfo: TextView
    private lateinit var ivFavorite: ImageView
    private lateinit var picture: Picture
    private var favFlag: Boolean = false

    private fun setView(){
        picture = requireArguments().getParcelable("selectedImage") ?: Picture()
        ivPictureInfo = requireView().findViewById(R.id.ivPictureInfo)
        txvInfo = requireView().findViewById(R.id.txvInfo)
        ivFavorite = requireView().findViewById(R.id.ivFavorite)

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
        (requireActivity() as MainActivity).replaceFragment(ImageFragment().apply {
            arguments = Bundle().apply {
                putParcelable("imageSource", picture)
            }
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