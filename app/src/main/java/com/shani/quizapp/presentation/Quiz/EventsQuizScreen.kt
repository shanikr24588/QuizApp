package com.shani.quizapp.presentation.Quiz

sealed class EventsQuizScreen {

    data class GetQuizzes(
        val numOfQuizzes: Int,
        val category:Int,
        val difficulty : String,
        val type : String
    ) :EventsQuizScreen()

    data class SetOptionSelected(
        val quizStateIndex: Int,
        val selectedOption : Int
    ):EventsQuizScreen()
}