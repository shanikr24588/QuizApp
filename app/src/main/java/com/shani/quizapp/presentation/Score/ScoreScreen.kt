package com.shani.quizapp.presentation.Score

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.shani.quizapp.presentation.utils.Dimens
import com.shani.quizapp.R
import com.shani.quizapp.presentation.nav_graph.Routes
import java.text.DecimalFormat

//@Preview
//@Composable
//fun prev(){
//    ScoreScreen(numOfQuestion = 5, numOfCorrectAns = 2)
//}

@Composable
fun ScoreScreen(
    numOfQuestion : Int,
    numOfCorrectAns:Int,
    navController: NavController
) {

    BackHandler {
        goToHome(navController)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.MediumPadding),
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            IconButton(
                onClick = { goToHome(navController) }
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Close",
                    tint = colorResource(id = R.color.blue_grey))
            }
        }
        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
                .background(colorResource(id = R.color.blue_grey)),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier.padding(
                    horizontal = Dimens.MediumPadding,
                    vertical = Dimens.MediumPadding

                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.congrats))
                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Black)){
                        append("You attempted ")
                    }
                    withStyle(style = SpanStyle(color = Color.Blue)){
                        append("$numOfQuestion question ")
                    }

                    withStyle(style = SpanStyle(color = Color.Black)){
                        append("and from that ")
                    }

                    withStyle(style = SpanStyle(color = colorResource(id = R.color.green))){
                        append("$numOfCorrectAns answers ")
                    }
                    withStyle(style = SpanStyle(color = Color.Black)){
                        append("are correct")
                    }
                }

                val scorePercentage = calculatePercentage(numOfCorrectAns, numOfQuestion)
                LottieAnimation(
                    modifier = Modifier.size(Dimens.LargeLottieAnimSize),
                    composition = composition,
                    iterations = 100
                )

                Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

                Text(
                    text = "Congrats!!",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.MediumTextSize,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

                Text(
                    text = "$scorePercentage% Score",
                    color = colorResource(id = R.color.green),
                    style= MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.LargeTextSize,
                    fontWeight = FontWeight.Bold

                )

                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

                Text(
                    text = "Quiz complete successfully",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

                Text(
                    text = annotatedString,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 18.sp,
                    textAlign =  TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Text(
                        text = "Share with us : ",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp,
                        textAlign =  TextAlign.Center,
                    )

                    Spacer(modifier = Modifier.width(Dimens.SmallSpacerWidth))

                    Image(
                        painter = painterResource(id = R.drawable.whatsapp),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )

                    Spacer(modifier = Modifier.width(Dimens.SmallSpacerWidth))

                    Image(
                        painter = painterResource(id = R.drawable.instagram),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )

                    Spacer(modifier = Modifier.width(Dimens.SmallSpacerWidth))

                    Image(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )

                }


            }
        }
    }
}

fun goToHome(navController: NavController){
    navController.navigate(Routes.HomeScreen.route){
        popUpTo(Routes.HomeScreen.route){
            inclusive = true
        }
    }
}

fun calculatePercentage(k:Int, n:Int): Double{
    require(k >= 0 && n > 0){"Invalid input : k must be non-negative and n must be positive"}

    val percentage = (k.toDouble()/n.toDouble()) * 100.0

    return DecimalFormat("#.##").format(percentage).toDouble()

}