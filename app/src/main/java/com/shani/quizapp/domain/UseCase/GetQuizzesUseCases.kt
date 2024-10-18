package com.shani.quizapp.domain.UseCase

import com.shani.quizapp.common.Resource
import com.shani.quizapp.domain.model.Quiz
import com.shani.quizapp.domain.repository.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetQuizzesUseCases(
    private val quizRepository: QuizRepository
) {
    operator fun invoke(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): Flow<Resource<List<Quiz>>> = flow {
        emit(Resource.Loading())
        try {
            val result = quizRepository.getQuizzes(amount, category, difficulty, type)
            result.fold(
                onSuccess = { quizzes ->
                    emit(Resource.Success(data = quizzes))
                },
                onFailure = { exception ->
                    emit(Resource.Error(message = exception.message ?: "An unknown error occurred"))
                }
            )
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "An unknown error occurred"))
        }
    }.flowOn(Dispatchers.IO)
}