package com.jc.collantes.emovie.presentation.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jc.collantes.emovie.data.model.ui.CustomChipData

@Composable
fun GroupChips(modifier: Modifier, customChipsList: List<CustomChipData>, isLoading : Boolean = false ) {
    if (isLoading){
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier) {
            items(listOf(1,1)){
                CustomChipShimmer()
            }
        }
    }else{
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier) {
            items(customChipsList) {
                CustomChip(it,isLoading = false)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GroupChipsPreview() {
    //GroupChips(Modifier.padding(start = 24.dp), listOf("1", "2", "3", "4", "5", "7"))
}