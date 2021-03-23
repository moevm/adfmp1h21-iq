package com.levi.iqtest.model.factorytest

import android.graphics.Paint
import com.levi.iqtest.model.AbstractQuestionFactory
import com.levi.iqtest.model.Answer
import com.levi.iqtest.model.Question
import com.levi.iqtest.model.drquestion.DrawQuestion1
import com.levi.iqtest.model.drquestion.DrawQuestion1Ans

class FactoryQA1() : AbstractQuestionFactory() {
    override fun generateQuestion(): Question {
        var question: Question
        var answer: List<Answer> = emptyList()

        //Color in the correct answer
        var color1: Paint = Paint()
        var color2: Paint = Paint()

        var dr = DrawQuestion1(color1, color2)
        var coeff_ans:Int = dr.getCoeff()

        val ans_wrong_rand:MutableList<Int> = mutableListOf(0,1,2,3)
        ans_wrong_rand.remove(coeff_ans)

        answer = answer.plus(Answer("", DrawQuestion1Ans(color1, color2), isCorrect = true))
        for(i in ans_wrong_rand.indices){
            val c2 = ans_wrong_rand[i] % 2
            val c1 = (ans_wrong_rand[i]  /2) %2

            answer = answer.plus(Answer("", DrawQuestion1Ans(pcolor(c1), pcolor(c2)), isCorrect = false))
        }

        question = Question("",  dr, answer,"")
        return question
    }

    protected fun pcolor(coeff:Int): Paint {
        if(coeff == 1)
            return Paint().apply { setARGB(255, 255, 255, 255)}
        else
            return Paint().apply { setARGB(255, 0, 0, 0)}
    }
}