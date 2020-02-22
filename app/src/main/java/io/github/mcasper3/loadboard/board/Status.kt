package io.github.mcasper3.loadboard.board

import androidx.annotation.StringRes
import io.github.mcasper3.loadboard.R

enum class Status(private val apiName: String, @StringRes val displayTextResId: Int) {
    AVAILABLE("available", R.string.available),
    BOOKED("booked", R.string.booked);

    companion object {
        fun from(apiName: String) = values().first { s -> s.apiName == apiName }
    }
}
