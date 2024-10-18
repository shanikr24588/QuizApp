package com.shani.quizapp.presentation.Quiz

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shani.quizapp.presentation.home.EventsHomeScreen
import com.shani.quizapp.presentation.utils.Dimens
import com.shani.quizapp.presentation.utils.Dimens.VerySmallPadding
import com.shani.quizapp.R
import com.shani.quizapp.presentation.common.ButtonBox
import com.shani.quizapp.presentation.nav_graph.Routes
import com.shani.quizapp.presentation.utils.Constants
import com.shani.quizapp.presentation.utils.Dimens.LargeSpacerHeight
import com.shani.quizapp.presentation.utils.Dimens.MediumCornerRadius
import com.shani.quizapp.presentation.utils.Dimens.SmallSpacerHeight
import com.shani.quizapp.presentation.utils.Dimens.VerySmallViewHeight
import kotlinx.coroutines.launch


//@Preview
//@Composable
//fun Prev(){
//    QuizScreen(
//        numOfQuiz = 1,
//        quizCategory = "Science",
//        quizDifficulty = "Hard",
//        quizType = "Multiple",
//         event = {}
//    )
//}

@Composable
fun QuizScreen(
    numOfQuiz : Int,
    quizCategory: String,
    quizDifficulty: String,
    quizType:String,
    event:(EventsQuizScreen)-> Unit,
    state: StateQuizScreen,
    navController: NavController
){

    LaunchedEffect(key1 = Unit) {
        val difficulty = when(quizDifficulty){
            "Medium" -> "medium"
            "Hard" -> "hard"
            else -> "easy"

        }
        val type = when(quizType){
            "Multiple Choice"-> "multiple"
            else -> "boolean"
        }
        event(EventsQuizScreen.GetQuizzes(numOfQuiz, Constants.categoriesMap[quizCategory]!!, difficulty, type))
    }

    Log.d("QuizScreen", "Collected QuizState: ${state.quizState.map { it.selectedOption }}")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        QuizAppBar(quizCategory = quizCategory) {

        }

        Column(
            modifier = Modifier
                .padding(VerySmallPadding)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(LargeSpacerHeight))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = numOfQuiz.toString(),
                    color = colorResource(id = R.color.blue_grey)
                )
//                Text(
//                    text = quizCategory,
//                    color = colorResource(id = R.color.blue_grey)
//                )

                Text(
                    text = quizDifficulty,
                    color = colorResource(id = R.color.blue_grey)
                )

                Text(
                    text = quizType,
                    color = colorResource(id = R.color.blue_grey)
                )
            }

            Spacer(modifier = Modifier.height(SmallSpacerHeight))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(VerySmallViewHeight)
                    .clip(RoundedCornerShape(MediumCornerRadius))
                    .background(colorResource(id = R.color.blue_grey))
            )

            Spacer(modifier = Modifier.height(LargeSpacerHeight))

            Log.d("QuizScreen", "Recomposition triggered. Current state: ${state.quizState.map { it.selectedOption }}")


            if (quizFetched(state)){

                val pagerState = rememberPagerState {
                    state.quizState.size
                }
                HorizontalPager(state = pagerState) {index->
                    QuizInterface(
                        modifier = Modifier.weight(1f),
                        quizState = state.quizState[index] ,
                        onOptionSelected = {selectedIndex->
                            Log.d("QuizScreens", "Triggering SetOptionSelected for Quiz $index, Option $selectedIndex")
                            event(EventsQuizScreen.SetOptionSelected(index, selectedIndex))
                            Log.d("QuizScreens", "Option selected: Quiz $index, Option $selectedIndex")},
                        qNumber = index + 1,
                        )
                }


                val buttonText by remember{
                    derivedStateOf {
                        when(pagerState.currentPage){
                            0 -> {
                                listOf("", "Next")
                            }
                            state.quizState.size - 1 ->{
                                listOf("Previous", "Submit")
                            }
                            else -> {
                                listOf("Previous", "Next")
                            }
                        }
                    }
                }

                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Dimens.MediumPadding)
                        .navigationBarsPadding()
                ) {
                    val scope = rememberCoroutineScope()

                    if (buttonText[0].isNotEmpty()){
                        ButtonBox(
                            text = "Previous" ,
                            padding = Dimens.SmallPadding,
                            fraction = 0.43f,
                            fontSize = 14.sp
                        ) {

                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage -1)
                            }

                        }
                    }


                    ButtonBox(
                        text = buttonText[1],
                        padding = Dimens.SmallPadding,
                        borderColor = colorResource(id = R.color.orange),
                        containerColor = if (pagerState.currentPage == state.quizState.size - 1) {
                            Log.d("ButtonBox", "Setting button color to orange")
                            colorResource(id = R.color.orange)
                        } else {
                            Log.d("ButtonBox", "Setting button color to Dark Slate Blue")
                            colorResource(id = R.color.Dark_slate_blue)
                        },
                        fraction = 1f,
                        textColor = colorResource(id = R.color.white),
                        fontSize = 14.sp
                    ) {
                        if(pagerState.currentPage == state.quizState.size - 1){
                             navController.navigate(Routes.ScoreScreen.passNumOfQuestionsAndCorrectAns(state.quizState.size, state.score))
                        }
                        else {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }


                    }

                }
            }

        }

    }


}

@Composable

fun quizFetched(state: StateQuizScreen): Boolean{
    return when {
        state.isLoading ->{
            ShimmerEffectQuizInterface()
            false
        }
        state.quizState?.isNotEmpty() == true ->{
            true
        }

        else ->{
            Text(text = state.error.toString(), color = colorResource(id = R.color.white))
            false
        }
    }
}