package com.levi.iqtest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.levi.iqtest.R

class HomeFragment : Fragment() {

//    private val homeViewModel: HomeViewModel by viewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)

//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        root.findViewById<Button>(R.id.btnToTraining).setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToTrainerFragment().setMode(0)
            findNavController().navigate(action)
        }
        root.findViewById<Button>(R.id.btnToTesting).setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToTrainerFragment().setMode(1)
            findNavController().navigate(action)
        }
        root.findViewById<Button>(R.id.btnToAbout).setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToAboutFragment()
            findNavController().navigate(action)
        }
        root.findViewById<Button>(R.id.btnLeaderboard).setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToLeaderboardFragment()
            findNavController().navigate(action)
        }
        return root
    }

    override fun onResume() {
        (activity as AppCompatActivity).supportActionBar?.hide()
        super.onResume()
    }

    override fun onPause() {
        (activity as AppCompatActivity).supportActionBar?.show()
        super.onPause()
    }
}
