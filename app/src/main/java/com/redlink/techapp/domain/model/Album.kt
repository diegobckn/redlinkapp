package com.redlink.techapp.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Album (
    val id: Int = 0,
    val title: String = ""
) : Parcelable{

}
