package com.example.a30daysofreading.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Quote(
    @StringRes val titleRes:Int,
    @DrawableRes val imageRes:Int,
    @StringRes val quoteRes:Int,
    @StringRes val authorRes:Int,
)
