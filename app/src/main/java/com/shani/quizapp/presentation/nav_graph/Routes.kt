package com.shani.quizapp.presentation.nav_graph

import com.shani.quizapp.presentation.home.EventsHomeScreen


const val  ARG_KEY_QUIZ_NUMBER = "ak_quiz_number"
const val  ARG_KEY_QUIZ_CATEGORY = "ak_quiz_category"
const val  ARG_KEY_QUIZ_DIFFICULTY = "ak_quiz_difficulty"
const val  ARG_KEY_QUIZ_TYPE = "ak_quiz_type"

const val NOQ_KEY = "noq_key"
const val CORRECT_ANS_KEY = "correct_ans_key"
//sealed class Routes(val route : String) {
//
//    object HomeScreen : Routes(route = "home_screen")
//
//    object QuizScreen : Routes(
//        route = "quiz_screen/{$ARG_KEY_QUIZ_NUMBER}" +
//                "/{$ARG_KEY_QUIZ_CATEGORY}" +
//                "/{$ARG_KEY_QUIZ_DIFFICULTY}" +
//                "/{$ARG_KEY_QUIZ_TYPE}"
//    ){
//        fun passQuizParams(
//            numsOfQuizzes : Int,
//            category: String,
//            difficulty : String,
//            type : String
//        ) : String{
//            return "quiz_screen/{$ARG_KEY_QUIZ_NUMBER}" +
//                    "/{$ARG_KEY_QUIZ_CATEGORY}" +
//                    "/{$ARG_KEY_QUIZ_DIFFICULTY}" +
//                    "/{$ARG_KEY_QUIZ_TYPE}"
//                        .replace(
//                        oldValue = "{$ARG_KEY_QUIZ_NUMBER}",
//                            newValue = numsOfQuizzes.toString()
//                    ).replace(
//                            oldValue =  "{$ARG_KEY_QUIZ_CATEGORY}",
//                            newValue = category
//                        ).replace(
//                            oldValue = "{$ARG_KEY_QUIZ_DIFFICULTY}",
//                            newValue = difficulty
//                        ).replace(
//                            oldValue =  "{$ARG_KEY_QUIZ_TYPE}" ,
//                            newValue = type
//                        )
//
//        }
//    }
//
//    object ScoreScreen : Routes(route = "score_screen")
//}

sealed class Routes(val route: String) {
    object HomeScreen : Routes("home_screen")
    object QuizScreen : Routes("quiz_screen/{$ARG_KEY_QUIZ_NUMBER}/{$ARG_KEY_QUIZ_CATEGORY}/{$ARG_KEY_QUIZ_DIFFICULTY}/{$ARG_KEY_QUIZ_TYPE}") {
        fun createRoute(numsOfQuizzes: Int, category: String, difficulty: String, type: String): String {
            return "quiz_screen/$numsOfQuizzes/$category/$difficulty/$type"
        }
    }
    object ScoreScreen : Routes("score_screen/{$NOQ_KEY}/{$CORRECT_ANS_KEY}"){
        fun passNumOfQuestionsAndCorrectAns(question :Int, correctAnswer: Int): String {
            return "score_screen/{$NOQ_KEY}/{$CORRECT_ANS_KEY}"
                .replace("{$NOQ_KEY}", question.toString())
                .replace("{$CORRECT_ANS_KEY}", correctAnswer.toString())
        }
    }
}