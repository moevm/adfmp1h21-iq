package com.levi.iqtest.model

abstract class AbstractQuestionFactory {
    abstract fun generateQuestion() : Question
}