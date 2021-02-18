package com.levi.iqtest.model

import com.levi.iqtest.model.Answer

class Question(val text: String, val image: String, val answers: List<Answer>, val explanation: String) {}