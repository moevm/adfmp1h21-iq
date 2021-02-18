package com.levi.iqtest.model

class Answer(answerText: String, val isCorrect: Boolean, var isChosen: Boolean = false) {
    val answerText:String = if (!answerText.startsWith("!@")) answerText else ""
    val answerImage:String = if (!answerText.startsWith("!@")) "" else answerText.substring(2)
}