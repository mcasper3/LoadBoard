package io.github.mcasper3.loadboard.board

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import io.github.mcasper3.loadboard.R

class LoadDetailsDialog : DialogFragment() {

    private lateinit var load: Load

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return layoutInflater.inflate(R.layout.dialog_load, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        load = checkNotNull(arguments?.getParcelable(LOAD_KEY))
        view.findViewById<TextView>(R.id.tv_origin).text = load.origin
        view.findViewById<TextView>(R.id.tv_destination).text = load.destination
        view.findViewById<TextView>(R.id.tv_date).text = load.date
        view.findViewById<TextView>(R.id.tv_value).text = load.value
        view.findViewById<TextView>(R.id.tv_equipment).text = load.equipment
        view.findViewById<TextView>(R.id.tv_locked).text = load.lockedStatus
        val isBookedSwitch = view.findViewById<Switch>(R.id.sw_booked)
        isBookedSwitch.isChecked = load.status == Status.BOOKED

        view.findViewById<View>(R.id.btn_cancel).setOnClickListener {
            dismiss()
        }
        view.findViewById<View>(R.id.btn_save).setOnClickListener {
            load.status = if (isBookedSwitch.isChecked) Status.BOOKED else Status.AVAILABLE
            (context as LoadBoardActivity).onLoadSaved(load)
            dismiss()
        }
    }

    companion object {

        private const val LOAD_KEY = "load"

        fun createInstance(load: Load) = LoadDetailsDialog().apply {
            arguments = Bundle().apply {
                putParcelable(LOAD_KEY, load)
            }
        }
    }
}
