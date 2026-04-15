package com.example.apphub.matchPointsCounter.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.apphub.R
import com.example.apphub.matchPointsCounter.domain.history.HistoryManager

class HistoryActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        listView = findViewById(R.id.listHistory)
        val btnClear = findViewById<Button>(R.id.btnClearHistory)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarHistory)

        btnVoltar.setOnClickListener { finish() }

        btnClear.setOnClickListener {
            HistoryManager.clearHistory(this)
            atualizar()
            Toast.makeText(this, "Histórico apagado", Toast.LENGTH_SHORT).show()
        }

        atualizar()
    }

    private fun atualizar() {
        val history = HistoryManager.getHistory(this)

        val adapter = object : ArrayAdapter<String>(this, R.layout.list_item_history, history) {
            override fun getView(pos: Int, conv: View?, parent: ViewGroup): View {
                val v = conv ?: LayoutInflater.from(context)
                    .inflate(R.layout.list_item_history, parent, false)

                val item = getItem(pos) ?: ""

                v.findViewById<TextView>(R.id.txtMatchResult).text = item

                v.findViewById<ImageButton>(R.id.btnDeleteMatch).setOnClickListener {
                    HistoryManager.deleteMatch(context, item)
                    atualizar()
                }

                return v
            }
        }

        val txtEmpty = findViewById<TextView>(R.id.txtEmpty)

        if (history.isEmpty()) {
            txtEmpty.visibility = View.VISIBLE
            listView.visibility = View.GONE
        } else {
            txtEmpty.visibility = View.GONE
            listView.visibility = View.VISIBLE
        }

        listView.adapter = adapter
    }
}