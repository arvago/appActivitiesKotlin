package com.example.practicaactivities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Picture(
    var id: Int? = null,
    var title: String = "",
    var description: String = "",
    var source: Int = 0,
    var favorite: Boolean? = null

) : Parcelable {
    companion object {
        val pictures = arrayOf(
            Picture(1, "Coco", "Palmera parlante de la Mansión Foster", R.drawable.coco, false),
            Picture(2, "Viejita", "Viejita de la Mansión Foster", R.drawable.viejita, false),
            Picture(3, "Mac", "Niño de la Mansión Foster", R.drawable.mac, false),
            Picture(4, "PlayStore", "Tienda de apps de Google", R.drawable.playstore, false),
            Picture(5, "TikTok", "Red Social para videos cortos", R.drawable.tiktok, false),
            Picture(6, "WhatsApp", "App para conversar", R.drawable.whatsapp, false)
        )
    }
}