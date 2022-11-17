package com.jc.collantes.emovie.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TagButton(text: String, modifier: Modifier, isLoading : Boolean = false) {
    if (isLoading){
        Text(
            text = "Tag", fontSize = 8.sp,
            modifier = modifier
                .background(buildDefaultShimmerBrush(), RoundedCornerShape(20))
                .padding(14.dp),
            color = Color.Transparent
        )
    }else{
        Button(
            modifier = modifier,
            colors =  ButtonDefaults.buttonColors(disabledContentColor = Color.Black, disabledBackgroundColor = Color.LightGray),
            enabled = false,
            onClick = {}) {
            Text(
                text = text,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(vertical = 2.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TagButtonPreview() {
    TagButton("es", Modifier.fillMaxHeight())
}