package com.bignerdranch.android.geoquiz

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_ocean, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0
    private val questionAnswered = BooleanArray(questionBank.size)

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val questionBankSize: Int
        get() = questionBank.size

    private var score: Int = 0

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {
        currentIndex = if (currentIndex == 0) {
            questionBank.size - 1
        } else {
            currentIndex - 1
        }
    }

    fun isCurrentQuestionAnswered(): Boolean {
        return questionAnswered[currentIndex]
    }

    fun questionAnswered() {
        questionAnswered[currentIndex] = true
    }

    fun increaseScore() : Int {
        return score++;
    }

    fun showScore(context: Context) {
        if (currentIndex == questionBankSize - 1 && isCurrentQuestionAnswered()) {
            Toast.makeText(context, "Your Score: $score", Toast.LENGTH_LONG).show()
        }
    }
}
