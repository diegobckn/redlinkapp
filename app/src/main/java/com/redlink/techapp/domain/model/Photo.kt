package com.redlink.techapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Photo (
    val id: Int = 0,
    val title: String = "",
    val thumbnailUrl: String = ""
) : Parcelable{
}
