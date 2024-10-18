package com.shani.quizapp.presentation.nav_graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shani.quizapp.presentation.Quiz.QuizScreen
import com.shani.quizapp.presentation.Quiz.QuizViewModel
import com.shani.quizapp.presentation.Score.ScoreScreen
import com.shani.quizapp.presentation.home.HomeScreen
import com.shani.quizapp.presentation.home.HomeScreenViewModel
import com.shani.quizapp.presentation.home.StateHomeScreen
import kotlinx.serialization.Serializer


@Composable


fun SetNavGraph() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {
        composable(route = Routes.HomeScreen.route) {
            val viewModel: HomeScreenViewModel = hiltViewModel()
            val state by viewModel.homeState.collectAsState()
            HomeScreen(
                state = state,
                event = viewModel::onEvent,
                navController = navController
            )
        }

        composable(
            route = Routes.QuizScreen.route,
            arguments = listOf(
                navArgument(ARG_KEY_QUIZ_NUMBER) { type = NavType.IntType },
                navArgument(ARG_KEY_QUIZ_CATEGORY) { type = NavType.StringType },
                navArgument(ARG_KEY_QUIZ_DIFFICULTY) { type = NavType.StringType },
                navArgument(ARG_KEY_QUIZ_TYPE) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val numberOfQuizzes = backStackEntry.arguments?.getInt(ARG_KEY_QUIZ_NUMBER) ?: 0
            val category = backStackEntry.arguments?.getString(ARG_KEY_QUIZ_CATEGORY) ?: ""
            val difficulty = backStackEntry.arguments?.getString(ARG_KEY_QUIZ_DIFFICULTY) ?: ""
            val type = backStackEntry.arguments?.getString(ARG_KEY_QUIZ_TYPE) ?: ""
             val quizVieModel : QuizViewModel = hiltViewModel()
            val state by quizVieModel.quizList.collectAsState()
            QuizScreen(
                numOfQuiz = numberOfQuizzes,
                quizCategory = category,
                quizDifficulty = difficulty,
                quizType = type,
                event = quizVieModel::onEvent,
                state = state,
                navController = navController

            )
        }

        composable(
            route = Routes.ScoreScreen.route,
            arguments = listOf(
                navArgument(NOQ_KEY){type = NavType.IntType},
                navArgument(CORRECT_ANS_KEY){type = NavType.IntType}
            ),
        ){
            val numOfQuestions = it.arguments?.getInt(NOQ_KEY)
            val numOfCorrectAns = it.arguments?.getInt(CORRECT_ANS_KEY)
            if (numOfQuestions != null) {
                if (numOfCorrectAns != null) {
                    ScoreScreen(
                        numOfQuestion = numOfQuestions,
                        numOfCorrectAns = numOfCorrectAns,
                        navController = navController
                    )
                }
            }
        }
    }
}