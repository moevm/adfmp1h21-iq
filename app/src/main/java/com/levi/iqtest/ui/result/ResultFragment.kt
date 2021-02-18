package com.levi.iqtest.ui.result

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.levi.iqtest.R
import com.levi.iqtest.databinding.FragmentResultBinding
import com.levi.iqtest.ui.result.ResultFragmentArgs
import com.levi.iqtest.ui.result.ResultFragmentDirections
import com.levi.iqtest.ui.trainer.TrainerViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {

    val args: ResultFragmentArgs by navArgs()
    private val viewModel: TrainerViewModel by navGraphViewModels(R.id.navigation_trainer)
    private val resultModel: ResultViewModel by viewModels()

    private fun saveResult(context: Context?) {
        val sharedPreferences: SharedPreferences? =
            context?.getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val scoreboard =
            sharedPreferences?.getStringSet("scoreboard", mutableSetOf<String>())?.map { it }
                ?.toMutableList()
        scoreboard?.add(scoreboard.size.toString() + "|" + resultModel?.name.value + "|" + viewModel.getScore())
        val editor = sharedPreferences?.edit()
        editor?.putStringSet("scoreboard", scoreboard?.toMutableSet())
        editor?.apply()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentResultBinding>(
            inflater,
            R.layout.fragment_result,
            container,
            false
        )
        binding.resultModel = resultModel

        val root = binding.root
//        val root = inflater.inflate(R.layout.fragment_result, container, false)
        root.findViewById<TextView>(R.id.txtResult).text = viewModel.getScore().toString()
        val btnRevise = root.findViewById<Button>(R.id.btnTestDetail)
        val txtName = root.findViewById<EditText>(R.id.txtName)

        if (args.showReviseBtn == 0) {
            btnRevise.visibility = Button.GONE
            txtName.visibility = EditText.VISIBLE
        } else {
            btnRevise.visibility = Button.VISIBLE
            txtName.visibility = EditText.GONE
            btnRevise.setOnClickListener {
                if (findNavController().currentDestination?.id == R.id.resultFragment) {
                    viewModel.currentQuestion.value = 0
                    viewModel.loadQuestion()
                    var direction = ResultFragmentDirections.actionResultFragmentToTrainerFragment()
                        .setMode(2)
                    findNavController().navigate(direction)
                }
            }
        }
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (findNavController().currentDestination?.id == R.id.resultFragment) {

                if (args.showReviseBtn == 0) {
                    saveResult(context)
                }
                val direction =
                    ResultFragmentDirections.actionResultFragmentToNavigationHome()
                findNavController().navigate(direction)
            }
        }
        root.findViewById<Button>(R.id.btnBackToMenu).setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.resultFragment) {
                if (args.showReviseBtn == 0) {
                    saveResult(context)
                }
                val direction =
                    ResultFragmentDirections.actionResultFragmentToNavigationHome()
                findNavController().navigate(direction)
            }
        }
        return root
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            ResultFragment()
    }
}