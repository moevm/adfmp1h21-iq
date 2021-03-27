package com.levi.iqtest.ui.trainer

import junit.framework.TestCase
import org.junit.Assert

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.runner.RunWith
import org.robolectric.util.PerfStatsCollector

@RunWith(AndroidJUnit4::class)
class TrainerViewModelTest {
    @Test
    fun loadQuestionList() {
        val application = ApplicationProvider.getApplicationContext() as Application
        val viewModel: TrainerViewModel = TrainerViewModel(application)
        viewModel.loadQuestionList()
        val questionList = viewModel.loadQuestionList()
        val answerList = viewModel.answerList
        Assert.assertNotNull(questionList)
        Assert.assertTrue(questionList.isNotEmpty())

        Assert.assertNotNull(answerList)
        Assert.assertTrue(answerList.isNotEmpty())
    }

    @Test
    fun loadPerQuestion(){
        val viewModel = TrainerViewModel(ApplicationProvider.getApplicationContext())
        val questionList = viewModel.loadQuestionList()
        for(i in 0 until QUESTION_PER_TEST){
            Assert.assertNotNull(questionList[i].image)
        }

        val observer = Observer<Int> {}
        try {
            // Observe the LiveData forever
            viewModel.currentQuestion.observeForever(observer)

            assertNotNull(viewModel.currentQuestion.value)
            assertNotNull(viewModel.currentAnswers.value)
            assertNotNull(viewModel.currentQuestionText.value)
            assertNotNull(viewModel.currentQuestionImage.value)
            assertNotNull(viewModel.currentExplanation.value)
        } finally {
            // Whatever happens, don't forget to remove the observer!
            viewModel.currentQuestion.removeObserver(observer)
        }
    }

    @Test
    fun loadAnswerPerQuestion(){
        val viewModel = TrainerViewModel(ApplicationProvider.getApplicationContext())
        val questionList = viewModel.loadQuestionList()
        for(i in 0 until QUESTION_PER_TEST){
            Assert.assertNotNull(questionList[i].answers)
            Assert.assertTrue(questionList[i].answers.size == 4)
        }
    }

    @Test
    fun getScore() {
        val application = ApplicationProvider.getApplicationContext() as Application
        val viewModel: TrainerViewModel = TrainerViewModel(application)
        Assert.assertTrue(viewModel.getScore() in 0..200)
    }

    @Test
    fun nextQuestion(){
        // Given a fresh ViewModel
        val viewModel = TrainerViewModel(ApplicationProvider.getApplicationContext())
        // Create observer - no need for it to do anything!
        val observer = Observer<Int> {}
        try {

            // Observe the LiveData forever
            viewModel.currentQuestion.observeForever(observer)

            val prev = viewModel.currentQuestion.value
            // When adding a new task
            for (i in 1 until QUESTION_PER_TEST){
                val result = viewModel.nextQuestion()
                assertTrue(result)
            }
            val result = viewModel.nextQuestion()
            assertFalse(result)
            assertNotNull(viewModel.currentQuestion.value)
            assertNotNull(viewModel.currentAnswers.value)
            assertNotNull(viewModel.currentQuestionText.value)
            assertNotNull(viewModel.currentQuestionImage.value)
            assertNotNull(viewModel.currentExplanation.value)

        } finally {
            // Whatever happens, don't forget to remove the observer!
            viewModel.currentQuestion.removeObserver(observer)
        }
    }

    @Test
    fun prevQuestion(){
        // Given a fresh ViewModel
        val viewModel = TrainerViewModel(ApplicationProvider.getApplicationContext())
        // Create observer - no need for it to do anything!
        val observer = Observer<Int> {}
        try {

            // Observe the LiveData forever
            viewModel.currentQuestion.observeForever(observer)

            val prev = viewModel.currentQuestion.value
            // When adding a new task
            var result= viewModel.prevQuestion()
            assertFalse(result)

            viewModel.nextQuestion()
            result= viewModel.prevQuestion()
            assertTrue(result)

            assertNotNull(viewModel.currentQuestion.value)
            assertNotNull(viewModel.currentAnswers.value)
            assertNotNull(viewModel.currentQuestionText.value)
            assertNotNull(viewModel.currentQuestionImage.value)
            assertNotNull(viewModel.currentExplanation.value)

        } finally {
            // Whatever happens, don't forget to remove the observer!
            viewModel.currentQuestion.removeObserver(observer)
        }
    }

}