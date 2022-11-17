package com.jc.collantes.emovie.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jc.collantes.emovie.R

@Composable
fun RatingButton(rate: Float, isEnable: Boolean, maxRange: Float, isLoading: Boolean = false) {

    if (isLoading){
        Text(
            text = "RatingButton", fontSize = 8.sp,
            modifier = Modifier
                .background(buildDefaultShimmerBrush(), RoundedCornerShape(20))
                .padding(14.dp),
            color = Color.Transparent
        )

    }else{
        val colors = when (rate) {
            in maxRange * 0.8f..maxRange -> ButtonDefaults.buttonColors(
                backgroundColor = Color.Green,
                contentColor = Color.Black
            )
            in maxRange * 0.6f..maxRange * 0.8f -> ButtonDefaults.buttonColors(
                backgroundColor = Color.Yellow,
                contentColor = Color.Black
            )
            else -> ButtonDefaults.buttonColors(backgroundColor = Color.Red, contentColor = Color.Black)
        }
        Button(
            colors = colors,
            enabled = isEnable,
            onClick = { /*TODO*/ }) {
            Row() {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = "#",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    text = rate.toString(),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatingButtonPreview() {
    RatingButton(10f, true, 10f)
}