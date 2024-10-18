package com.shani.quizapp.presentation.Quiz

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shani.quizapp.presentation.utils.Dimens
import com.shani.quizapp.R

@Preview
@Composable
fun ShimmerEffectQuizInterface(){
    Column {
        Row(
            modifier = Modifier.padding(Dimens.SmallPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .height(40.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .weight(0.1f)
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.width(10.dp))

            Box(
                modifier = Modifier
                    .width(20.dp)
                    .height(40.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .weight(0.9f)
                    .shimmerEffect()
            )
        }
        Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))
        Column(
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.MediumBoxHeight)
                    .clip(RoundedCornerShape(Dimens.ExtraLargeCornerRadius))
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.MediumBoxHeight)
                    .clip(RoundedCornerShape(Dimens.ExtraLargeCornerRadius))
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.MediumBoxHeight)
                    .clip(RoundedCornerShape(Dimens.ExtraLargeCornerRadius))
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.MediumBoxHeight)
                    .clip(RoundedCornerShape(Dimens.ExtraLargeCornerRadius))
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.height(Dimens.ExtraLargeSpacerHeight))

            Row {
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxWidth()
                        .height(Dimens.MediumBoxHeight)
                        .clip(RoundedCornerShape(Dimens.ExtraLargeCornerRadius))
                        .shimmerEffect()

                )

                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxWidth()
                        .height(Dimens.MediumBoxHeight)
                        .clip(RoundedCornerShape(Dimens.ExtraLargeCornerRadius))
                        .shimmerEffect()
                )
            }
        }
    }
}

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = "")
    val alpha = transition.animateFloat(
        initialValue = 0.2f ,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    ).value

    background(colorResource(id = R.color.blue_grey).copy(alpha = alpha))
}