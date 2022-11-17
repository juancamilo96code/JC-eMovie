package com.jc.collantes.emovie.presentation.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jc.collantes.emovie.R

@Composable
fun Logo(modifier : Modifier){
    Image(painter = painterResource(R.drawable.logojc),
        contentDescription = "eMovie",
        modifier = modifier,
    )
}

@Preview
@Composable
fun LogoPreview(){
    Logo(Modifier.padding(vertical = 0.dp))
}