package com.levi.iqtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TableLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class LeaderboardFragment : Fragment() {

    companion object {
        fun newInstance() = LeaderboardFragment()
    }

    val args: LeaderboardFragmentArgs by navArgs()
    private val viewModel: LeaderboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_leaderboard, container, false)
        val table = root.findViewById<TableLayout>(R.id.tableResult)
        root.findViewById<TabLayout>(R.id.tabResult)
            .addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewModel.loadData(tab.position)
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {}
                override fun onTabReselected(tab: TabLayout.Tab) {}
            })
        viewModel.scoreboard.observe(viewLifecycleOwner, Observer {
            table.removeAllViews()
            it.forEach { entry ->
                val row = inflater.inflate(R.layout.result_row, table, false)
                row.findViewById<TextView>(R.id.txtRowName).text = entry.name
                row.findViewById<TextView>(R.id.txtRowScore).text = entry.score.toString()
                row.findViewById<TextView>(R.id.txtRowTime).text =
                    android.text.format.DateFormat.format("mm:ss", entry.time).toString()
                table.addView(row, MATCH_PARENT, WRAP_CONTENT)
            }
        })
        viewModel.loadData(args.mode)
        return root
    }

}