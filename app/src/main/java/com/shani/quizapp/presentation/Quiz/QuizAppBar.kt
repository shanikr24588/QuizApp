package com.shani.quizapp.presentation.Quiz

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import com.shani.quizapp.R
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizAppBar(
    quizCategory : String,
    onBackClick:()-> Unit,
) {

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorResource(id = R.color.Dark_slate_blue),
            actionIconContentColor = colorResource(id = R.color.blue_grey),
            navigationIconContentColor = colorResource(id = R.color.blue_grey)
        ),
        title = {
            Text(
                text = quizCategory,
                color = colorResource(id = R.color.blue_grey),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },

        navigationIcon = {
            IconButton(onClick = onBackClick) {
                 Icon(
                     imageVector = Icons.Default.ArrowBack,
                     contentDescription = null,
                     )
            }
        }


    )
}