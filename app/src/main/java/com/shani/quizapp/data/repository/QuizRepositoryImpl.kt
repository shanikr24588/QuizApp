package com.shani.quizapp.data.repository

import android.util.Log
import com.shani.quizapp.data.remote.QuizApi
import com.shani.quizapp.domain.model.Quiz
import com.shani.quizapp.domain.repository.QuizRepository

class QuizRepositoryImpl(private val quizApi: QuizApi) : QuizRepository {
    override suspend fun getQuizzes(amount: Int, category: Int, difficulty: String, type: String): Result<List<Quiz>> {
        return try {
            val response = quizApi.getQuizzes(amount, category, difficulty, type)
            Log.d("QuizRepository", "Response code: ${response.code()}")
            Log.d("QuizRepository", "Response body: ${response.body()}")

            if (response.isSuccessful) {
                val quizResponse = response.body()
                if (quizResponse != null) {
                    Log.d("QuizRepository", "Quiz results: ${quizResponse.results}")
                    Result.success(quizResponse.results)
                } else {
                    Log.e("QuizRepository", "Response body was null")
                    Result.failure(Exception("Response body was null"))
                }
            } else {
                Log.e("QuizRepository", "API call failed with code ${response.code()}")
                Result.failure(Exception("API call failed with code ${response.code()}"))
            }
        } catch (e: Exception) {
            Log.e("QuizRepository", "Exception occurred: ${e.message}")
            Result.failure(e)
        }
    }
}