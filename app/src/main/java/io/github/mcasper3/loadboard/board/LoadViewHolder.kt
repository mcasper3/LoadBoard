package io.github.mcasper3.loadboard.board

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.mcasper3.loadboard.R

class LoadViewHolder(
    view: View,
    private val onClickAction: (load: Load) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val originTextView = view.findViewById<TextView>(R.id.tv_origin)
    private val destinationTextView = view.findViewById<TextView>(R.id.tv_destination)
    private val dateTextView = view.findViewById<TextView>(R.id.tv_date)
    private val valueTextView = view.findViewById<TextView>(R.id.tv_value)
    private val equipmentTextView = view.findViewById<TextView>(R.id.tv_equipment)
    private val lockedTextView = view.findViewById<TextView>(R.id.tv_locked)
    private val statusTextView = view.findViewById<TextView>(R.id.tv_status)

    private lateinit var load: Load

    init {
        view.setOnClickListener {
            onClickAction(load)
        }
    }

    fun bind(load: Load) {
        this.load = load
        originTextView.text = load.origin
        destinationTextView.text = load.destination
        dateTextView.text = load.date
        valueTextView.text = load.value
        equipmentTextView.text = load.equipment
        lockedTextView.text = load.lockedStatus
        statusTextView.setText(load.status.displayTextResId)
    }
}
