package com.jc.collantes.emovie.presentation.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem

@Composable
fun CategoryCarousel(
    modifier: Modifier,
    moviesList: List<SimpleMovieItem>? = listOf(),
    isLoading: Boolean = false,
    onItemSelected: (Long) -> Unit
) {

    if (isLoading) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier) {
            val modifierItem = Modifier
                .width(138.dp)
                .height(180.dp)
            items(listOf(1, 1, 1)) {
                CatalogMovieItemShimmer(modifier = modifierItem)
            }
        }

    } else {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier) {
            val modifierItem = Modifier
                .width(138.dp)
                .height(180.dp)
            if (moviesList != null){
                items(moviesList) {
                    CatalogMovieItem(it,modifierItem,onItemSelected= onItemSelected)
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun CategoryCarouselPreview() {
    // CategoryCarousel(Modifier.padding(start = 25.dp), listOf(1, 2, 3, 4, 5, 7))
}