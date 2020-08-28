package com.redlink.techapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Album (
    val id: Int = 0,
    val title: String = ""
) : Parcelable{
}
