package com.example.practicaactivities

import android.content.Intent
import android.media.MediaPlayer
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
    private lateinit var ivFavoriteSound: ImageView
    private var picture = Picture
    private lateinit var pictures: Array<Picture>
    var index = 0
    private var favFlag: Boolean = false
    private var favSoundFlag: Boolean = false

    private fun setView(){
        pictures = requireArguments().getParcelableArray("ImagesArray") as Array<Picture>
        index = requireArguments().getInt("selectedImage")
        ivPictureInfo = requireView().findViewById(R.id.ivPictureInfo)
        txvInfo = requireView().findViewById(R.id.txvInfo)
        ivFavorite = requireView().findViewById(R.id.ivFavorite)
        ivFavoriteSound = requireView().findViewById(R.id.ivFavoriteSound)

        ivPictureInfo.setImageResource(pictures[index].source)
        txvInfo.text = pictures[index].title + ": " + pictures[index].description

        if(pictures[index].favorite == false){
            ivFavorite.setImageResource(R.drawable.estrella)
        }else{
            ivFavorite.setImageResource(R.drawable.estrella_rellena)
        }

        if(pictures[index].favSound == false){
            ivFavoriteSound.setImageResource(R.drawable.corazon)
        }else{
            ivFavoriteSound.setImageResource(R.drawable.corazon_relleno)
        }

        playSound(pictures[index].sound)

        ivPictureInfo.setOnClickListener{
            showImage()
        }
        ivFavorite.setOnClickListener {
            addToFavorites()
        }
        ivFavoriteSound.setOnClickListener {
            addToFavSound()
        }
    }

    private fun showImage(){
        (requireActivity() as MainActivity).replaceFragment(ImageFragment().apply {
            arguments = Bundle().apply {
                putParcelable("imageSource", pictures[index])
            }
        })
    }

    private fun addToFavorites(){
        if(pictures[index].favorite == false && !favFlag){
            ivFavorite.setImageResource(R.drawable.estrella_rellena)
            favFlag = true

            picture.pictures[index].favorite = true
            picture.pictures.forEach {
                if(it.id == pictures[index].id){
                    it.favorite = true
                    pictures[index].favorite = true
                }else{
                    it.favorite = false
                }
            }
        }else{
            ivFavorite.setImageResource(R.drawable.estrella)
            favFlag = false

            picture.pictures[index].favorite = false
            picture.pictures.forEach {
                if(it.id == pictures[index].id){
                    it.favorite = false
                    pictures[index].favorite = false
                }
            }
        }
        (requireActivity() as MainActivity).preferences.edit().putString((requireActivity() as MainActivity).PIC_PREFERENCES, (requireActivity() as MainActivity).moshi.adapter(Picture::class.java).toJson(picture.pictures[index])).commit()
    }

    private fun addToFavSound(){
        if(pictures[index].favSound == false && !favSoundFlag){
            ivFavoriteSound.setImageResource(R.drawable.corazon_relleno)
            favSoundFlag = true

            picture.pictures[index].favSound = true
            picture.pictures.forEach {
                if(it.id == pictures[index].id){
                    it.favSound = true
                    pictures[index].favSound= true
                }else{
                    it.favSound = false
                }
            }
        }else{
            ivFavoriteSound.setImageResource(R.drawable.corazon)
            favSoundFlag = false

            picture.pictures[index].favSound = false
            picture.pictures.forEach {
                if(it.id == pictures[index].id){
                    it.favSound = false
                    pictures[index].favSound= false
                }
            }
        }
        //(requireActivity() as MainActivity).preferences.edit().putString((requireActivity() as MainActivity).PIC_PREFERENCES, (requireActivity() as MainActivity).moshi.adapter(Picture::class.java).toJson(picture.pictures[index])).commit()
    }

    private fun playSound(sound: Int) = MediaPlayer.create(context, sound).start()

}