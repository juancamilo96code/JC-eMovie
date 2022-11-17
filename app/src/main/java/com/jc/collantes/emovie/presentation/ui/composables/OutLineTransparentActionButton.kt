package com.jc.collantes.emovie.presentation.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OutLineTransparentActionButton(
    text: String,
    modifier: Modifier,
    isLoading: Boolean = false,
    onClicked: () -> Unit
) {

    if (isLoading) {
        Text(
            text = "Tag", fontSize = 8.sp,
            modifier = modifier
                .background(buildDefaultShimmerBrush(), RoundedCornerShape(20))
                .padding(14.dp),
            color = Color.Transparent
        )
    } else {
        TextButton(
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = Color.White
            ),
            border = BorderStroke(1.dp, Color(0x66FFFFFF)),
            onClick = { onClicked() }) {
            Text(text = text, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
fun OutLineTransparentActionButtonPreview() {
    OutLineTransparentActionButton("OutLine", Modifier.background(Color.Green)){}
}