package com.jc.collantes.emovie.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomChipShimmer() {
    Text(
        text = "CustomChip", fontSize = 8.sp,
        modifier = Modifier
            .background(buildDefaultShimmerBrush(), RoundedCornerShape(50))
            .padding(15.dp),
        color = Color.Transparent
    )
}


@Preview(showBackground = true)
@Composable
fun CustomChipShimmerPreview() {
    CustomChipShimmer()
}