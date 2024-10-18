package com.shani.quizapp.presentation.home


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class HomeScreenViewModel: ViewModel() {

    private val _homeState = MutableStateFlow(StateHomeScreen())
    val homeState = _homeState

    fun onEvent(event : EventsHomeScreen){
        when(event){

            is EventsHomeScreen.setNumberOfQuizzes -> {
                _homeState.value = homeState.value.copy(numberOfQuiz = event.numberOfQuizzes)

            }
            is EventsHomeScreen.setOfQuizCategory -> {
                _homeState.value = homeState.value.copy(category = event.category)

            }
            is EventsHomeScreen.setQuizDifficulty -> {

                _homeState.value = homeState.value.copy(difficulty = event.difficulty)

            }

            is EventsHomeScreen.setQuizType -> {
                _homeState.value = homeState.value.copy(type = event.type)

            }
            else -> {}
        }
    }
}