package com.example.link.models.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
): Parcelable