package com.levi.iqtest.ui.about

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.fragment.findNavController
import com.levi.iqtest.R

class AboutFragment : Fragment() {

    companion object {
        fun newInstance() = AboutFragment()
    }

    private lateinit var viewModel: AboutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_about, container, false)
        root.findViewById<AppCompatImageButton>(R.id.btnBackToMenu).setOnClickListener{
            if (findNavController().currentDestination?.id == R.id.aboutFragment) {
                val direction =
                    ActionOnlyNavDirections(R.id.actionAboutFragmentToNavigationHome)
                findNavController().navigate(direction)
            }
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AboutViewModel::class.java)
        // TODO: Use the ViewModel
    }

}