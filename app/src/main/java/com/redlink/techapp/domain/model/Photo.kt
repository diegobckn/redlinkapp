package com.redlink.techapp.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Photo (
    val id: Int = 0,
    val title: String = "",
    val thumbnailUrl: String = ""
) : Parcelable{

}
