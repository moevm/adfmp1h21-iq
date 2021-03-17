package com.levi.iqtest.model.factorytest

import android.util.Log
import com.levi.iqtest.model.AbstractQuestionFactory
import com.levi.iqtest.model.Answer
import com.levi.iqtest.model.Question
import com.levi.iqtest.model.drquestion.DrawQuestion4

class FactoryQA4(): AbstractQuestionFactory() {
    override fun generateQuestion(): Question {
        var question: Question
        var answer:List<Answer> = emptyList()
        val n:Int = (5..8).random()
        val pos_question:Int = (0..n).random()

        val x:Int = (10..30).random()
        val a = IntArray(n+1) {100 + x*it}

        answer.plus(Answer(a[pos_question].toString(), null, isCorrect = true))
        answer.plus(Answer((a[pos_question] + (0..10).random()).toString(),null, isCorrect = false))
        answer.plus(Answer((a[n-1] + x + 1).toString(),null, isCorrect = false))
        answer.plus(Answer((a[pos_question] - 2).toString(),null, isCorrect = false))

        question = Question("", DrawQuestion4(a, n, pos_question),answer,"")
        return question
    }
}