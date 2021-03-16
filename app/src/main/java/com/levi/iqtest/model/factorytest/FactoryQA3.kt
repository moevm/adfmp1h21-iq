package com.levi.iqtest.model.factorytest

import com.levi.iqtest.model.AbstractQuestionFactory
import com.levi.iqtest.model.Answer
import com.levi.iqtest.model.Question
import com.levi.iqtest.model.drquestion.DrawQuestion3
import com.levi.iqtest.model.drquestion.DrawQuestion3Ans
import kotlin.random.Random

class FactoryQA3() : AbstractQuestionFactory() {
//    private val n:Int = 4

    protected fun createMatrix(n:Int): Array<IntArray>{
        val a:Array<IntArray> = Array(n) { IntArray(n) {0}}

        for (i in 0 until n - 1) {
            for (j in 0 until n - 1) {
                a[i][j] = (1..10).random()
                a[n - 1][j] += a[i][j]
            }
        }
        for (i in 0 until n) {
            for (j in 0 until n - 1) {
                a[i][n - 1] += a[i][j]
            }
        }
        return a
    }

    override fun generateQuestion():Question{
        var question: Question
        var answer:List<Answer> = emptyList()

        val n:Int = 4
        val a:Array<IntArray> = createMatrix(n)
        val res:IntArray = IntArray(3)

        for(i in 0 until res.size){
            res[i] = a[i+1][i+1]
            a[i+1][i+1] = 0
        }

        val wrong_res1 = IntArray(3) { Random.nextInt(10, 30) }
        val wrong_res2 = IntArray(3) { Random.nextInt(10, 40) }
        val wrong_res3 = IntArray(3) { Random.nextInt(5, 20) }

//        var dr = DrawQuestion3(a, n)
//        var corect_ans = DrawQuestion3Ans(res)

        answer.plus(Answer("", DrawQuestion3Ans(res), isCorrect = true))
        answer.plus(Answer("", DrawQuestion3Ans(wrong_res1), isCorrect = false))
        answer.plus(Answer("", DrawQuestion3Ans(wrong_res2), isCorrect = false))
        answer.plus(Answer("", DrawQuestion3Ans(wrong_res3), isCorrect = false))

        question = Question("",  DrawQuestion3(a, n), answer,"")

        return question
    }
}