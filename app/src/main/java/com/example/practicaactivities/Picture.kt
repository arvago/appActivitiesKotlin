package com.example.practicaactivities

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
class Picture(
    var title: String = "",
    var description: String = "",
    var source: Int = 0,
    var favorite: Boolean? = null

) : Parcelable {
    companion object {
        val pictures = arrayOf(
            Picture("Coco", "Palmera parlante de la Mansion Foster", R.drawable.coco, false),
            Picture("Viejita", "Viejita de la Mansion Foster", R.drawable.viejita, false),
            Picture("Mac", "Niño de la Mansion Foster", R.drawable.mac, false),
            Picture("PlayStore", "Tienda de apps de Google", R.drawable.playstore, false),
            Picture("TikTok", "Red Social para videos cortos", R.drawable.tiktok, false),
            Picture("WhatsApp", "App para conversar", R.drawable.whatsapp, false)
        )
    }
}