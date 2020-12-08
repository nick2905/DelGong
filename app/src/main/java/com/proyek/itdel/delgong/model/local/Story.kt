package com.proyek.itdel.delgong.model.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Story(
    val title: String,
    val author: String,
    val yearPublish:String,
    val imgPath: Int,
    val partStory: ArrayList<PartStory>,
    val type: String
) : Parcelable

@Parcelize
data class PartStory(
    var storiesValue: String,
    var pathAudio: String,
    var imgPartPath: Int,
    var indexStory: Int
): Parcelable

