package com.shani.quizapp.presentation.Quiz

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shani.quizapp.R
import com.shani.quizapp.presentation.utils.Dimens

@Composable
fun QuizInterface(
    onOptionSelected: (Int)-> Unit,
    qNumber: Int,
    quizState: QuizState,
    modifier : Modifier = Modifier
){

    val question = quizState.quiz?.question!!.replace("&quot;", "\"").replace("&#039;", "\'")

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.wrapContentHeight()
        ) {
           Row(
               modifier = Modifier.fillMaxWidth()
           ){
               Text(
                   modifier = Modifier.weight(1f),
                   text = "$qNumber",
                   color = colorResource(id = R.color.blue_grey),
                   fontSize =  16.sp
               )

               Text(
                   modifier = Modifier.weight(9f),
                   text =  question,
                   color = colorResource(id = R.color.blue_grey),
                   fontSize =  16.sp
               )
           }
            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

            Column(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                val options = listOf(
                    "A" to quizState.shuffleOption[0].replace("&quot;", "\"").replace("&#039;", "\'"),
                    "B" to quizState.shuffleOption[1].replace("&quot;", "\"").replace("&#039;", "\'"),
                    "C" to  quizState.shuffleOption[2].replace("&quot;", "\"").replace("&#039;", "\'"),
                    "D" to  quizState.shuffleOption[3].replace("&quot;", "\"").replace("&#039;", "\'"),
                )

                Column {
                    options.forEachIndexed { index, (optionNumber, optionText) ->
                        if (optionText.isNotEmpty()) {
                            QuizOption(
                                optionNumber = optionNumber,
                                option = optionText,
                                onOptionClick = {
                                    Log.d("QuizInterface", "Option selected: $index")
                                    onOptionSelected(index)

                                },
                                selected = quizState.selectedOption == index,
                                onUnselectedOption = {
                                    onOptionSelected(-1)
                                    Log.d("QuizInterface", "Option unselected")
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
                    }

                }
                Spacer(modifier = Modifier.height(Dimens.ExtraLargeSpacerHeight))
            }
        }

    }
}