package com.levi.iqtest

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

class LeaderboardFragment : Fragment() {

    companion object {
        fun newInstance() = LeaderboardFragment()
    }

    private val viewModel: LeaderboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_leaderboard, container, false)
        val table = root.findViewById<TableLayout>(R.id.tableResult)
        viewModel.scoreboard.observe(viewLifecycleOwner, Observer {
            table.removeAllViews()
            it.forEach { entry ->
                val row = inflater.inflate(R.layout.result_row, table, false)
                row.findViewById<TextView>(R.id.txtRowName).text = entry.name
                row.findViewById<TextView>(R.id.txtRowScore).text = entry.score.toString()
                table.addView(row, MATCH_PARENT, WRAP_CONTENT)
            }
        })
        return root
    }

}