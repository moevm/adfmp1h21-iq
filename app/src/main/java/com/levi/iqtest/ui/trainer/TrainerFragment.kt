package com.levi.iqtest.ui.trainer

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.*
import android.widget.*
import androidx.activity.addCallback
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.levi.iqtest.R
import com.levi.iqtest.model.Answer
import com.levi.iqtest.ui.AnswersAdapterGV
import com.levi.iqtest.ui.dialog.ExitDialogFragment
import com.levi.iqtest.ui.dialog.PreparationTimeFragment
import com.levi.iqtest.ui.dialog.TimeoutDialogFragment
import kotlinx.android.synthetic.main.fragment_trainer.*


class TrainerFragment : Fragment() {

    companion object {
        fun newInstance() = TrainerFragment()
    }

    val args: TrainerFragmentArgs by navArgs()
    private val viewModel: TrainerViewModel by navGraphViewModels(R.id.navigation_trainer)
    var timer: CountDownTimer? = null
    var timeLeft: Long = 0
    var txtTimer: TextView? = null
    var totalTime: Long = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_trainer, container, false)
        setHasOptionsMenu(true)
        val txtQuestionNumber = root.findViewById<TextView>(R.id.txtQuestionNumber)
        val txtQuestionText = root.findViewById<TextView>(R.id.txtQuestionText)
        val imgQuestionImage = root.findViewById<ImageView>(R.id.imgQuestionImage)
//        val txtImage = root.findViewById<TextView>(R.id.txtImage)
        val txtExplanation = root.findViewById<TextView>(R.id.txtExplanation)
        val gvAnswers = root.findViewById<GridView>(R.id.gvAnswers)
        if (args.mode != 2) {
            viewModel.mode = args.mode
        }
        gvAnswers.numColumns = 2
//        rvAnswers.layoutManager = GridLayoutManager(context,2)
//        (rvAnswers.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        viewModel.currentQuestion.observe(viewLifecycleOwner, Observer {
            txtQuestionNumber.text = "№ " + (it + 1).toString()
            btnPrevQuestion.visibility = if (it == 0) Button.INVISIBLE else Button.VISIBLE

        })
        viewModel.currentQuestionText.observe(viewLifecycleOwner, Observer {
            txtQuestionText.text = it
        })
        viewModel.currentExplanation.observe(viewLifecycleOwner, Observer {
            txtExplanation.text = it
        })
        if (args.mode == 0 || args.mode == 1) {
            txtExplanation.visibility = TextView.GONE
        } else if (args.mode == 2) {
            txtExplanation.visibility = TextView.VISIBLE
        }

        viewModel.currentQuestionImage.observe(viewLifecycleOwner, Observer {
//            txtImage.text = it
            if (it != null) {
                imgQuestionImage.visibility = ImageView.VISIBLE
                imgQuestionImage.setImageDrawable(it)
//                Log.i("Debug",it.toString())
//                val resId = context?.resources?.getIdentifier(
//                    "iqtest_" + it.substring(2),
//                    "drawable",
//                    context?.packageName
//                )
//                resId?.let { id ->
//                    imgQuestionImage.setImageResource(id)
//                }
            } else {
                imgQuestionImage.visibility = ImageView.GONE
                imgQuestionImage.setImageDrawable(null)
            }
        })


        viewModel.currentAnswers.observe(viewLifecycleOwner, Observer {
            val adapter =
                AnswersAdapterGV(args.mode, context, it, this@TrainerFragment::adapterOnClick)
            gvAnswers.adapter = adapter
//            setDynamicHeight(gvAnswers, 2)
        })

        root.findViewById<AppCompatImageButton>(R.id.btnBackToMenu).setOnClickListener{
            if (args.mode == 0 || args.mode == 1) {
                ExitDialogFragment(
                    "Are you sure you want to exit? Your progress will be loss.",
                    {
                        if (findNavController().currentDestination?.id == R.id.trainerFragment) {

                            val direction =
                                TrainerFragmentDirections.actionTrainerFragmentToNavigationHome()
                            findNavController().navigate(direction)
                        }
                    },
                    {}).show(parentFragmentManager, "exit")
            } else {
                findNavController().popBackStack()
            }
        }

        root.findViewById<Button>(R.id.btnNextQuestion).setOnClickListener {
            nextQuestion()
        }
        root.findViewById<Button>(R.id.btnPrevQuestion).setOnClickListener {
            viewModel.prevQuestion()
        }
        txtTimer = root.findViewById(R.id.txtTimer)
        if (args.mode == 0) {
            timeLeft = Long.MAX_VALUE
        } else if (args.mode == 1) {
            timeLeft = 12000
        }
//        timerStart(timeLeft)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (args.mode == 0 || args.mode == 1) {
                ExitDialogFragment(
                    "Are you sure you want to exit? Your progress will be loss.",
                    {
                        if (findNavController().currentDestination?.id == R.id.trainerFragment) {

                            val direction =
                                TrainerFragmentDirections.actionTrainerFragmentToNavigationHome()
                            findNavController().navigate(direction)
                        }
                    },
                    {}).show(parentFragmentManager, "exit")
            } else {
                findNavController().popBackStack()
            }
        }
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        if(args.mode==1) {
//            inflater.inflate(R.menu.trainer_menu, menu)
//            txtTimer = menu.findItem(R.id.txtTimer)
//
//        }else {
        super.onCreateOptionsMenu(menu, inflater)
//        }
    }

    fun timerStart(timeLengthMilli: Long) {
        timer = object : CountDownTimer(timeLengthMilli, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                if (args.mode == 0) {
                    totalTime += 1000
                    viewModel.time = totalTime
                    txtTimer?.text =
                        android.text.format.DateFormat.format("mm:ss", totalTime)
                            .toString()
                } else if (args.mode == 1) {
                    viewModel.time = millisUntilFinished
                    txtTimer?.text =
                        android.text.format.DateFormat.format("mm:ss", millisUntilFinished)
                            .toString()
                }
            }

            override fun onFinish() {
                TimeoutDialogFragment("Time over!") {
                    if (findNavController().currentDestination?.id == R.id.trainerFragment) {
                        val action =
                            TrainerFragmentDirections.actionTrainerFragmentToResultFragment()
                                .setShowReviseBtn(0)
                        findNavController().navigate(action)
                    }
                }.show(parentFragmentManager, "timeout")
            }
        }
        timer?.start()
    }

    override fun onPause() {
        if (args.mode != 2)
            timer?.cancel()
        super.onPause()
    }

    override fun onResume() {
        if (args.mode != 2) {
            val dialog = PreparationTimeFragment() {
                timerStart(timeLeft)
            }
            dialog.isCancelable = false
            dialog.show(parentFragmentManager, "startCountdown")
        }
        super.onResume()
    }

    private fun setDynamicHeight(gridView: GridView, col: Int) {
        val gridViewAdapter = gridView.adapter
            ?: // pre-condition
            return
        var totalHeight = gridView.measuredHeight
        val items = gridViewAdapter.count
        var rows = 1
        var x = 1f
        if (items > col) {
            x = items / col.toFloat()
            rows = if (items % col != 0) {
                (x + 1).toInt()
            } else {
                x.toInt()
            }
        }
        val rowHeight = totalHeight / rows
        for (i in 0 until items) {
            val listItem = gridViewAdapter.getView(i, null, gridView)
            listItem.findViewById<ImageView>(R.id.imgAnswer).minimumHeight = rowHeight
//            listItem.measure(0, 0)
//            totalHeight =
//                if (listItem.measuredHeight > totalHeight) listItem.measuredHeight else totalHeight
        }
//        val params = gridView.layoutParams
//        params.
//        params.height = totalHeight
//        gridView.layoutParams = params
    }

    private fun nextQuestion() {
        if (!viewModel.nextQuestion()) {
//                TODO: confirmation to end test/training
            if (args.mode == 0) {
                ExitDialogFragment("Finish?", {
                    if (findNavController().currentDestination?.id == R.id.trainerFragment) {
                        val action =
                            TrainerFragmentDirections.actionTrainerFragmentToResultFragment()
                                .setShowReviseBtn(1)
                        findNavController().navigate(action)
                    }
                }, {}).show(parentFragmentManager, "exit")
            } else if (args.mode == 1) {
                ExitDialogFragment(
                    "Are you sure you want to finish the test? There is time remaining",
                    {
                        if (findNavController().currentDestination?.id == R.id.trainerFragment) {
                            val action =
                                TrainerFragmentDirections.actionTrainerFragmentToResultFragment()
                                    .setShowReviseBtn(0)
                            findNavController().navigate(action)
                        }
                    },
                    {}).show(parentFragmentManager, "exit")
            } else if (args.mode == 2) {
                if (findNavController().currentDestination?.id == R.id.trainerFragment) {
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun adapterOnClick(id: Int, ans: Answer) {
        if (args.mode == 0 || args.mode == 1) {
            viewModel.currentQuestion.value?.let {
                if (viewModel.answerList[it] != null) {
                    viewModel.answerList[it]?.isChosen = false
                }
                viewModel.answerList[it] = ans
                viewModel.answerList[it]?.isChosen = true

                (gvAnswers.adapter as AnswersAdapterGV).notifyDataSetChanged()
            }
//        nextQuestion()
            Log.i("Answer chosen", ans.answerText + ans.answerImage)
            Log.i("Score", viewModel.getScore().toString())
        }
    }
}