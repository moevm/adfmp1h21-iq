package com.levi.iqtest.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.levi.iqtest.R
import com.levi.iqtest.model.Answer

class AnswersAdapterGV (val mode: Int, val context: Context?, var answers: List<Answer>, private val onClick: (Int, Answer)->Unit) : BaseAdapter() {
    val layoutInflater: LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val root = layoutInflater.inflate(R.layout.my_answer_button,null)
        Log.i("aaaa","aaaa")
        val layout: LinearLayout = root.findViewById(R.id.layout)
        val textView : TextView = root.findViewById(R.id.txtAnswer)
        val imageView : ImageView = root.findViewById(R.id.imgAnswer)
        val answerId : TextView = root.findViewById(R.id.txtAnswerId)

        layout.setBackgroundResource(0)
        if(answers[p0].isChosen){
            layout.setBackgroundResource(R.drawable.border_green)
        }

        if(mode==2) {
            if (answers[p0].isChosen) {
                layout.setBackgroundResource(R.drawable.border_red)
            }
            if(answers[p0].isCorrect){
                layout.setBackgroundResource(R.drawable.border_green)
            }
        }
        answerId.text = ('A'+p0).toString()
        val context = root.context
        textView.visibility = TextView.GONE
        imageView.visibility = ImageView.GONE
        if(answers[p0].answerImage.isNotEmpty()){
            imageView.visibility = ImageView.VISIBLE
            val resId = context?.resources?.getIdentifier("iqtest_"+answers[p0].answerImage,"drawable", context?.packageName)
            resId?.let {
                    id -> imageView.setImageResource(id)
            }
        }
        if(answers[p0].answerText.isNotEmpty()){
            textView.visibility = TextView.VISIBLE
            imageView.setImageDrawable(null)
            textView.text = answers[p0].answerText
        }
        root.setOnClickListener {
            onClick(p0, answers[p0])
        }
        return root
    }

    override fun getItem(p0: Int): Any {
        return answers[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return answers.size
    }

}