package com.shani.quizapp.data.remote.dto

import com.shani.quizapp.domain.model.Quiz

data class QuizResponse(
    val response_code: Int,
    val results: List<Quiz>
)