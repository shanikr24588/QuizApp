package com.shani.quizapp.presentation.home

 sealed class EventsHomeScreen {
     data class setNumberOfQuizzes(val numberOfQuizzes: Int) : EventsHomeScreen()
     data class setOfQuizCategory(val category: String) : EventsHomeScreen()
     data class setQuizDifficulty(val difficulty: String) : EventsHomeScreen()
     data class setQuizType(val type: String) : EventsHomeScreen()

}