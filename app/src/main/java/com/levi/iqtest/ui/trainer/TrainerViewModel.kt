package com.levi.iqtest.ui.trainer

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.levi.iqtest.model.Answer
import com.levi.iqtest.model.Question

const val QUESTION_PER_TEST = 10

class TrainerViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext
    private var questionsData: List<Question>
    private var questionList: List<Question>
    var answerList: MutableList<Answer?>
    val currentQuestion = MutableLiveData<Int>()
    val currentQuestionText = MutableLiveData<String>()
    val currentQuestionImage = MutableLiveData<String>()
    val currentAnswers = MutableLiveData<List<Answer>>()
    val currentExplanation = MutableLiveData<String>()

    fun loadQuestionList(): List<Question>{
        val inputStream = context.resources.openRawResource(
            context.resources.getIdentifier(
                "iqtest_data",
                "raw",
                context.packageName
            )
        )
        return csvReader().readAllWithHeader(inputStream).map { row ->
            Question(row["text"]!!,
                row["image"]!!,
                listOfNotNull(
                    row["answer1"],
                    row["answer2"],
                    row["answer3"],
                    row["answer4"],
                    row["answer5"],
                    row["answer6"],
                    row["answer7"]
                ).filter { value -> value.isNotBlank() }
                    .mapIndexed { index, value ->
                        Answer(
                            value,
                            index + 1 == row["correct"]?.toInt()
                        )
                    }.shuffled(),
                row["explanation"]!!
            )
        }
    }
    init {
//      create a sublist of question
        questionsData = loadQuestionList()
        questionList = questionsData.shuffled().take(QUESTION_PER_TEST)
        answerList = MutableList(QUESTION_PER_TEST) { null }

        currentQuestion.value = 0
//      assign question to appropriate variables
        loadQuestion()
    }

    fun getScore(): Int{
        return 100 + answerList.sumBy { ans -> if(ans==null) 0 else if (ans.isCorrect) +10 else -10 }
    }
    fun loadQuestion() {
        currentQuestion.value?.let {
            currentAnswers.value = questionList[it].answers
            currentQuestionText.value = questionList[it ].text
            currentQuestionImage.value = questionList[it ].image
            currentExplanation.value = questionList[it].explanation
        }
    }

    fun nextQuestion(): Boolean {
        currentQuestion.value?.let {
            if (it+1 < questionList.size) {
                currentQuestion.value = it + 1
                loadQuestion()
                return true
            }
        }
        return false
    }
    fun prevQuestion(): Boolean {
        currentQuestion.value?.let {
            return if (it-1 < 0) {
                false
            }else{
                currentQuestion.value = it - 1
                loadQuestion()
                true
            }
        }
        return false
    }
}