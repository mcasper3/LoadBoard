package io.github.mcasper3.loadboard.board

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.mcasper3.loadboard.R

class LoadAdapter(
    private val onClickAction: (load: Load) -> Unit
) : RecyclerView.Adapter<LoadViewHolder>() {

    var loads = emptyList<Load>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LoadViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_load, parent, false),
            onClickAction
        )

    override fun getItemCount() = loads.size

    override fun onBindViewHolder(holder: LoadViewHolder, position: Int) =
        holder.bind(loads[position])

    override fun getItemId(position: Int) = loads[position].id
}
