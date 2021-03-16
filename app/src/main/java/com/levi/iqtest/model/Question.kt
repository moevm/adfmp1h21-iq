package com.levi.iqtest.model

import android.graphics.drawable.Drawable
import com.levi.iqtest.model.Answer

class Question(val text: String, val image: Drawable, val answers: List<Answer>, val explanation: String) {
}