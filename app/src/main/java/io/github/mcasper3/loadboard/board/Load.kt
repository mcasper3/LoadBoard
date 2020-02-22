package io.github.mcasper3.loadboard.board

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Load(
    val id: Long,
    val origin: String,
    val destination: String,
    val date: String,
    val value: String,
    val equipment: String,
    val isLocked: Boolean,
    var status: Status
) : Parcelable {

    @IgnoredOnParcel
    val lockedStatus = if (isLocked) "Yes" else "No"
}
