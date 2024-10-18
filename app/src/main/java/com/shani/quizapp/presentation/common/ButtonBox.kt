package com.shani.quizapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

import com.shani.quizapp.presentation.utils.Dimens
import com.shani.quizapp.R
import com.shani.quizapp.presentation.utils.Dimens.MediumTextSize

@Composable
fun ButtonBox(
    modifier:Modifier = Modifier,
    text:String,
    padding:Dp = Dimens.SmallPadding,
    borderColor: Color = colorResource(id = R.color.blue_grey),
    containerColor:Color = colorResource(id = R.color.Dark_slate_blue),
    textColor: Color = colorResource(id = R.color.black),
    fontSize : TextUnit = MediumTextSize,
    fraction: Float = 1f,
    onClick : ()-> Unit
){
     Box(
         modifier = Modifier
             .padding(padding)
             .border(2.dp, borderColor, RoundedCornerShape(Dimens.LargeCornerRadius))
             .clickable { onClick() }
             .fillMaxWidth(fraction)
             .height(Dimens.MediumBoxHeight)
             .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
             .background(colorResource(id = R.color.blue_grey)),
         contentAlignment = Alignment.Center,

         ){
          Text(
              text = text,
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
              fontSize =  fontSize,
              color = textColor,

          )
     }
}