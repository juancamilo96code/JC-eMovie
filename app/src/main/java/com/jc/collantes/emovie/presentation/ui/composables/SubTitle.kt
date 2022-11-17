package com.jc.collantes.emovie.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SubTitle(text : String, modifier : Modifier, isLoading : Boolean = false){
    if (isLoading){
        Text(modifier = modifier.background(buildDefaultShimmerBrush(), RoundedCornerShape(20f)),
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Transparent
        )
    }else{
        Text(modifier = modifier,
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }

}

@Preview(showBackground = true)
@Composable
fun SubTitlePreview(){
    SubTitle(text = "SubTitle", Modifier.padding(start = 24.dp))
}