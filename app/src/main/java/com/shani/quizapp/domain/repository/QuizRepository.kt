package com.shani.quizapp.domain.repository

import com.shani.quizapp.domain.model.Quiz

interface QuizRepository {
    suspend fun getQuizzes(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): Result<List<Quiz>>
}