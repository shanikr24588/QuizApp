package com.shani.quizapp.presentation.Quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shani.quizapp.common.Resource
import com.shani.quizapp.domain.UseCase.GetQuizzesUseCases
import com.shani.quizapp.domain.model.Quiz
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuizzesUseCases: GetQuizzesUseCases
) : ViewModel() {

    private val _quizList = MutableStateFlow(StateQuizScreen())
    val quizList: StateFlow<StateQuizScreen> = _quizList

    fun onEvent(event: EventsQuizScreen) {
        Log.d("QuizViewModel", "onEvent called with: $event")
        when (event) {
            is EventsQuizScreen.GetQuizzes -> {
                getQuizzes(event.numOfQuizzes, event.category, event.difficulty, event.type)
            }

            is EventsQuizScreen.SetOptionSelected -> {
                Log.d("QuizViewModel5", "Before SetOptionSelected event")
                Log.d("QuizViewModel5", "Received SetOptionSelected event: Quiz ${event.quizStateIndex}, Option ${event.selectedOption}")
                updateQuizStateList(event.quizStateIndex, event.selectedOption)
                Log.d("QuizViewModel5", "After SetOptionSelected event")
            }

            else-> {
                Log.d("QuizViewModel5", "Unhandled event: $event")
            }
        }
    }

    private fun updateQuizStateList(quizStateIndex:Int, selectedOption: Int){
        val updatedQuizStateList = mutableListOf<QuizState>()

        quizList.value.quizState.forEachIndexed{ index, quizState ->
            updatedQuizStateList.add(
                if(quizStateIndex == index){
                    quizState.copy(selectedOption = selectedOption)
                }
                else{
                    quizState

                }
            )
        }
        Log.d("QuizViewModel", "Updating state: Quiz $quizStateIndex, Option $selectedOption")
       Log.d("QuizViewModel", "New state: ${updatedQuizStateList.map { it.selectedOption }}")
        _quizList.value = quizList.value.copy(quizState = updatedQuizStateList)

        updateScore(_quizList.value.quizState[quizStateIndex])
    }

    private fun updateScore(quizState: QuizState) {

        if(quizState.selectedOption != -1){
            val correctAnswer = quizState.quiz?.correct_answer
            val selectedAnswer = quizState.selectedOption?.let {
                quizState.shuffleOption[it].replace("&quot;", "\"").replace("&#039;", "\'")
            }

            if (correctAnswer == selectedAnswer){
                val previousScore = _quizList.value.score
                _quizList.value = quizList.value.copy(score = previousScore + 1)
            }
        }



    }


    private fun getQuizzes(amount: Int, category: Int, difficulty: String, type: String) {
        Log.d("QuizViewModel", "getQuizzes called with: amount=$amount, category=$category, difficulty=$difficulty, type=$type")
        viewModelScope.launch {
            getQuizzesUseCases(amount, category, difficulty, type).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        Log.d("QuizViewModel", "Loading...")
                        _quizList.value = StateQuizScreen(isLoading = true)
                    }
                    is Resource.Success -> {
                        Log.d("QuizViewModel", "Success: Raw API response: ${resource.data}")
                        if (resource.data.isNullOrEmpty()) {
                            Log.e("QuizViewModel", "API returned empty or null data")
                            _quizList.value = StateQuizScreen(error = "No quizzes found")
                        } else {
                            val listOfQuizState = getListOfQuizState(resource.data)
                            Log.d("QuizViewModel", "Processed QuizList: $listOfQuizState")
                            _quizList.value = StateQuizScreen(quizState = listOfQuizState)
                        }
                    }
                    is Resource.Error -> {
                        Log.e("QuizViewModel", "Error: ${resource.message}")

                        Log.e("QuizViewModel", "Stack trace: ${resource.message?.let { Exception(it).stackTraceToString() }}")
                        _quizList.value = StateQuizScreen(error = resource.message.toString())
                    }
                }
            }
        }
    }



    private fun getListOfQuizState(data: List<Quiz>?): List<QuizState> {
        val listOfQuizState = mutableListOf<QuizState>()

        data?.forEach { quiz ->
            val shuffledOptions = mutableListOf<String>().apply {
                add(quiz.correct_answer)
                addAll(quiz.incorrect_answers)
                shuffle()
            }

            listOfQuizState.add(QuizState(quiz, shuffledOptions, -1))
        }
        Log.d("QuizViewModel", "Initial QuizState: ${listOfQuizState.map { it.selectedOption }}")

        return listOfQuizState
    }

}