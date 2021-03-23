package com.levi.iqtest.model.factorytest

import com.levi.iqtest.model.AbstractQuestionFactory
import com.levi.iqtest.model.Answer
import com.levi.iqtest.model.Question
import com.levi.iqtest.model.drquestion.DrawQuestion2
import com.levi.iqtest.model.drquestion.DrawQuestion2Ans
import kotlin.random.Random

class FactoryQA2() : AbstractQuestionFactory(){
    protected fun createMatrix(n:Int):Array<IntArray> {
        val coeff:Int = (5..10).random()
        val a:Array<IntArray> = Array(n) { IntArray(n) }
        a[0][0] = 1*coeff
        for (i in 0 until n) {
            if (i == 0) for (j in 1 until n) a[0][j] = a[0][j - 1] + (n - j)*coeff
            else for (j in 0 until n) a[i][j] = a[i - 1][j] + i*coeff
        }
        return a
    }
    override fun generateQuestion(): Question {
        var question: Question
        var answer:List<Answer> = emptyList()

        val n:Int = 4
        val a:Array<IntArray> = createMatrix(n)
        val res:IntArray = IntArray(3)

        for(i in 1 until res.size+1){
            res[i-1] = a[i-1][i]
            a[i-1][i] = 0
        }

        val wrong_res1 = IntArray(3) { Random.nextInt(0, 50) }
        val wrong_res2 = IntArray(3) { Random.nextInt(0, 50) }
        val wrong_res3 = IntArray(3) { Random.nextInt(0, 50) }

//        var dr = DrawQuestion2(a, n)
//        var corect_ans = DrawQuestion2Ans(res)

        answer = answer.plus(Answer("", DrawQuestion2Ans(res), isCorrect = true))
        answer = answer.plus(Answer("", DrawQuestion2Ans(wrong_res1), isCorrect = false))
        answer = answer.plus(Answer("", DrawQuestion2Ans(wrong_res2), isCorrect = false))
        answer = answer.plus(Answer("", DrawQuestion2Ans(wrong_res3), isCorrect = false))

        question = Question("",  DrawQuestion2(a, n), answer,"")

        return question
    }
}