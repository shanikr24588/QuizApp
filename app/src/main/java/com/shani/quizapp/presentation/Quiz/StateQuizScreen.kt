package com.shani.quizapp.presentation.Quiz

import com.shani.quizapp.domain.model.Quiz

data class StateQuizScreen (
    val isLoading : Boolean = false,
    val quizState: List<QuizState> = emptyList(),
    val error : String = "",
    val score : Int = 0
)

data class QuizState(
    val quiz : Quiz ? = null,
    val shuffleOption : List<String> = emptyList(),
    val selectedOption :Int ? = -1
)