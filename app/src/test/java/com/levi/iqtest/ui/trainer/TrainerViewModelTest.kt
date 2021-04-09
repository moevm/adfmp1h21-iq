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
import com.levi.iqtest.model.AbstractQuestionFactory
import com.levi.iqtest.model.Question
import com.levi.iqtest.model.factorytest.*
import org.junit.runner.RunWith
//import org.robolectric.util.PerfStatsCollector

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
    fun generateQuestion(){
//        val viewModel = TrainerViewModel(ApplicationProvider.getApplicationContext())
        var listFactory : List<AbstractQuestionFactory> = listOf(
            FactoryQA1(),
            FactoryQA2(),
            FactoryQA3(),
            FactoryQA4(),
            FactoryQA5()
        )
        for(i in 0 until QUESTION_PER_TEST){
            val rand = (0..listFactory.size-1).random()
            val factory = listFactory.get(rand).generateQuestion()

            Assert.assertNotNull(factory)
            Assert.assertNotNull(factory.text)
            Assert.assertNotNull(factory.image)
            Assert.assertTrue(factory.answers.size == 4)
            Assert.assertNotNull(factory.explanation)
        }
    }

    @Test
    fun loadAnswerPerTypeQuestion(){
//        val viewModel = TrainerViewModel(ApplicationProvider.getApplicationContext())
        var listFactory : List<AbstractQuestionFactory> = listOf(
            FactoryQA1(),
            FactoryQA2(),
            FactoryQA3(),
            FactoryQA4(),
            FactoryQA5()
        )
        for(i in 0 until 5){
            val factory = listFactory.get(i).generateQuestion()
            Assert.assertNotNull(factory)
            Assert.assertTrue(factory.answers.size == 4)
            for(j in 0 until factory.answers.size){
                val ans = factory.answers[j]
                if(i<3){
                    // drawable ansewer
                    Assert.assertNotNull(ans.answerImage)
                    Assert.assertTrue(ans.answerText == "")
                }else{
                    // text ansewer
                    Assert.assertNotNull(ans.answerText)
                    Assert.assertNull(ans.answerImage)
                }
            }
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