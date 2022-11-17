package com.jc.collantes.emovie.presentation.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jc.collantes.emovie.data.model.ui.CustomChipData


@Composable
fun CustomChip(
    customChipData: CustomChipData, isLoading: Boolean = false) {
    if (isLoading) {
        CustomChipShimmer()
    } else {
        val colors = if (customChipData.isActive) {
            ButtonDefaults.buttonColors(
                backgroundColor = Color.White, contentColor = Color.Black
            )
        } else {
            ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent, contentColor = Color.White
            )
        }
        TextButton(colors = colors,
            shape = RoundedCornerShape(50),
            border = BorderStroke(1.dp, Color.White),
            onClick = {
                customChipData.onCheckedChange(!customChipData.isActive)
            }) {
            Text(text = customChipData.text, fontSize = 12.sp)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CustomChipPreview() {
    CustomChip(CustomChipData("filterYear", "Lanzadas en 1996", "1996",false) {}, true)
}