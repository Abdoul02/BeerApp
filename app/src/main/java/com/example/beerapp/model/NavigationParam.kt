package com.example.beerapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NavigationParam(
    val description: String,
    val name: String,
    val imageLink: String,
    val tip: String
):Parcelable