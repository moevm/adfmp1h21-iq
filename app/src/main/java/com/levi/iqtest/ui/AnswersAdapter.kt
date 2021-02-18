package com.levi.iqtest.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.levi.iqtest.R
import com.levi.iqtest.model.Answer

class AnswersAdapter (private val onClick: (Int, Answer)->Unit) : ListAdapter<Answer,AnswersAdapter.ViewHolder>(AnswersListDiffCallback()) {
    class ViewHolder(itemView: View, val onClick: (Int, Answer)->Unit): RecyclerView.ViewHolder(itemView){
        val layout: LinearLayout = itemView.findViewById(R.id.layout)
        val textView : TextView = itemView.findViewById(R.id.txtAnswer)
        val imageView : ImageView = itemView.findViewById(R.id.imgAnswer)
        val answerId : TextView = itemView.findViewById(R.id.txtAnswerId)
        var currentAnswer: Answer? = null
        var currentAnswerId: Int? = null

        init {
            itemView.setOnClickListener {
                onClick(currentAnswerId!!, currentAnswer!!)
            }
        }

        fun bind(id:Int, answer: Answer) {
            if(answer.isChosen){
                layout.setBackgroundResource(R.drawable.border_green)
            }else{
                layout.setBackgroundResource(0)
            }
            currentAnswerId = id
            currentAnswer = answer
            answerId.text = ('A'+id).toString()
            val context = itemView.context
            textView.visibility = TextView.GONE
            imageView.visibility = ImageView.GONE
            if(answer.answerImage.isNotEmpty()){
                imageView.visibility = ImageView.VISIBLE
                val resId = context?.resources?.getIdentifier("iqtest_"+answer.answerImage,"drawable", context?.packageName)
                resId?.let {
                        id -> imageView.setImageResource(id)
                }
            }
            if(answer.answerText.isNotEmpty()){
                textView.visibility = TextView.VISIBLE
                imageView.setImageDrawable(null)
                textView.text = answer.answerText
            }
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.my_answer_button, viewGroup, false)
        return ViewHolder(view, onClick)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        viewHolder.setIsRecyclable(false)
        Log.i("Debug", "Binded")
        viewHolder.bind(position, getItem(position))
    }
}

class AnswersListDiffCallback : DiffUtil.ItemCallback<Answer>(){
    override fun areItemsTheSame(oldItem: Answer, newItem: Answer): Boolean {
        return (oldItem.answerImage == newItem.answerImage) && (oldItem.answerText == newItem.answerText)
    }

    override fun areContentsTheSame(oldItem: Answer, newItem: Answer): Boolean {
        return (oldItem.answerImage == newItem.answerImage) && (oldItem.answerText == newItem.answerText)
    }

}