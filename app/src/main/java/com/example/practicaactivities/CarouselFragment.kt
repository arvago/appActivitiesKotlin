package com.example.practicaactivities

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView

class CarouselFragment : Fragment(R.layout.fragment_carousel) {
    override fun onResume() {
        super.onResume()
        initView()
    }

    private lateinit var ivPicture: ImageView
    private lateinit var btnBack: Button
    private lateinit var btnNext: Button
    private lateinit var btnInfo: Button
    private var contadorCarousel = 0
    private var contLimit = Picture.pictures.size

    private fun getChanges(): Picture {
        return (requireActivity() as MainActivity).preferences.getString( (requireActivity() as MainActivity).PIC_PREFERENCES, null)?.let {
            return@let try {
                (requireActivity() as MainActivity).moshi.adapter(Picture::class.java).fromJson(it)
            } catch (e: Exception) {
                Picture()
            }
        } ?: Picture()

    }

    private fun initView(){
        ivPicture = requireView().findViewById(R.id.ivPicture)
        btnBack = requireView().findViewById(R.id.btnBack)
        btnNext = requireView().findViewById(R.id.btnNext)
        btnInfo = requireView().findViewById(R.id.btnInfo)

        btnBack.setOnClickListener{
            lastImage()
        }
        btnNext.setOnClickListener{
            nextImage()
        }
        btnInfo.setOnClickListener{
            infoImage()
        }

        Picture.pictures.forEach {
            if(it.id == getChanges().id){
                it.favorite = getChanges().favorite
                it.favSound = getChanges().favSound
            }
        }

        getChanges()
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
        (requireActivity() as MainActivity).replaceFragment(InfoFragment().apply {
            arguments = Bundle().apply {
                //putParcelable("selectedImage", Picture.pictures[contadorCarousel])
                putParcelableArray("ImagesArray", Picture.pictures)
                putInt("selectedImage", contadorCarousel)
            }
        })
    }


}