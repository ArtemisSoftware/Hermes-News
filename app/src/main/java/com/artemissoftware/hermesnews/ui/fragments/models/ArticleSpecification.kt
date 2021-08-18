package com.artemissoftware.hermesnews.ui.fragments.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleSpecification (
    val url: String
) : Parcelable