package io.github.mcasper3.loadboard.board

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.mcasper3.loadboard.R

class LoadBoardActivity : AppCompatActivity() {

    private var currentLoads = arrayListOf<Load>()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_board)

        if (savedInstanceState != null && savedInstanceState.containsKey(LOADS_KEY)) {
            this.currentLoads = savedInstanceState.getParcelableArrayList(LOADS_KEY)!!
        } else {
            // LoadRetriever#getLoads should typically be called on a background thread to avoid
            // locking the UI while processing
            this.currentLoads = LoadRetriever().getLoads()
        }

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = LoadAdapter(this::showDialog).apply {
            loads = currentLoads
        }
    }

    private fun showDialog(load: Load) {
        LoadDetailsDialog.createInstance(load).show(supportFragmentManager, "loadDetails")
    }

    fun onLoadSaved(load: Load) {
        recyclerView.adapter?.notifyItemChanged(currentLoads.indexOf(load))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(LOADS_KEY, currentLoads)
        super.onSaveInstanceState(outState)
    }

    companion object {

        private const val LOADS_KEY = "loads"
    }
}
