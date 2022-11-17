package com.jc.collantes.emovie.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CatalogMovieItemShimmer(modifier: Modifier){
    Spacer(modifier = modifier
        .clip(RoundedCornerShape(20f))
        .background(buildDefaultShimmerBrush()))
}

@Preview(showBackground = true)
@Composable
fun CatalogMovieItemShimmerPreview() {
    CatalogMovieItemShimmer(Modifier.width(138.dp).height(180.dp))
}

