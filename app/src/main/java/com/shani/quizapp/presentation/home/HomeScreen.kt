package com.shani.quizapp.presentation.home


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.shani.quizapp.presentation.common.AppDropDownMenu
import com.shani.quizapp.presentation.common.ButtonBox
import com.shani.quizapp.presentation.home.component.HomeHeader
import com.shani.quizapp.presentation.nav_graph.Routes
import com.shani.quizapp.presentation.utils.Dimens.MediumSpacerHeight
import com.shani.quizapp.presentation.utils.Constants
import com.shani.quizapp.presentation.utils.Dimens.MediumPadding
import com.shani.quizapp.presentation.utils.Dimens.SmallSpacerHeight


@Composable
fun HomeScreen(
    state : StateHomeScreen,
    event : (EventsHomeScreen) -> Unit,
    navController: NavController
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HomeHeader()
//
        Spacer(modifier = Modifier.height(MediumSpacerHeight))

        AppDropDownMenu(
            menuName = "Number of Questions:",
            menuList = Constants.numberAsString,
            text = state.numberOfQuiz.toString(),
            onDropDownClick = {event(EventsHomeScreen.setNumberOfQuizzes(it.toInt()))}
        )

        Spacer(modifier = Modifier.height(SmallSpacerHeight))

        AppDropDownMenu(
            menuName = "Select Category:",
            menuList = Constants.categories,
            text = state.category,
            onDropDownClick = {event(EventsHomeScreen.setOfQuizCategory(it))}
        )

        Spacer(modifier = Modifier.height(SmallSpacerHeight))

        AppDropDownMenu(
            menuName = "Select Difficulty:",
            menuList = Constants.difficulty,
            text = state.difficulty,
            onDropDownClick = {event(EventsHomeScreen.setQuizDifficulty(it))}
        )

        Spacer(modifier = Modifier.height(SmallSpacerHeight))

        AppDropDownMenu(
            menuName = "Select Type:",
            menuList = Constants.type,
            text = state.type,
            onDropDownClick = {event(EventsHomeScreen.setQuizType(it))}
        )

        Spacer(modifier = Modifier.height(MediumSpacerHeight))

        ButtonBox(text = "Generate Quiz", padding = MediumPadding) {
            navController.navigate(
                Routes.QuizScreen.createRoute(
                    numsOfQuizzes = state.numberOfQuiz,
                    category = state.category,
                    difficulty = state.difficulty,
                    type = state.type
                )
            )
        }

    }
}