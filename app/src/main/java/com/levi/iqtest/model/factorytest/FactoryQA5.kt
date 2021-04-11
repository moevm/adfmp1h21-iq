package com.levi.iqtest.model.factorytest

import com.levi.iqtest.model.AbstractQuestionFactory
import com.levi.iqtest.model.Answer
import com.levi.iqtest.model.Question
import com.levi.iqtest.model.drquestion.DrawQuestion5

class FactoryQA5() : AbstractQuestionFactory(){
    override fun generateQuestion(): Question {
        var question: Question
        var answer:List<Answer> = emptyList()
        val n:Int = (5..12).random()

//        var corect_ans:Answer = Answer(n.toString(), isCorrect = true)
//        var wrong_ans1:Answer = Answer((n+1).toString(), isCorrect = false)
//        var wrong_ans2:Answer = Answer((n-1).toString(), isCorrect = false)
//        var wrong_ans3:Answer = Answer((n+2).toString(), isCorrect = false)

        answer = answer.plus(Answer(n.toString(), null, isCorrect = true))
        answer = answer.plus(Answer((n+1).toString(), null, isCorrect = false))
        answer = answer.plus(Answer((n-1).toString(), null, isCorrect = false))
        answer = answer.plus(Answer((n+2).toString(), null, isCorrect = false))
        answer = answer.shuffled()

        question = Question("",  DrawQuestion5(n), answer,"")
        return question
    }
}
