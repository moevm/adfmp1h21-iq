package com.levi.iqtest.model

import android.graphics.drawable.Drawable

class Answer(answerText: String?, image: Drawable?, val isCorrect: Boolean, var isChosen: Boolean = false) {
    val answerText: String? = answerText //if (!answerText.startsWith("!@")) answerText else ""
    val answerImage: Drawable? = image //if (!answerText.startsWith("!@")) "" else answerText.substring(2)
}