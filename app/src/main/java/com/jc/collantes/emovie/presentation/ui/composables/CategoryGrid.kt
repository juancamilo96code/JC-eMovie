package com.jc.collantes.emovie.presentation.ui.composables


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.roundToInt
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem
import com.jc.collantes.emovie.utils.ImageSizer


@Composable
fun CategoryGrid(
    modifier: Modifier, moviesList: List<SimpleMovieItem>? = listOf(),
    isLoading: Boolean = false,onItemSelected: (Long) -> Unit
) {

    val itemWidthSize = if ((LocalConfiguration.current.screenWidthDp - 32) >= 155 * 2) {
        val numItems = ((LocalConfiguration.current.screenWidthDp - 32f) / 155f).roundToInt()
        (LocalConfiguration.current.screenWidthDp - 32f) / numItems
    } else {
        LocalConfiguration.current.screenWidthDp - 32f
    }
    val itemHeightSize = ImageSizer().getHeightByWidth(itemWidthSize)

    FlowRow(
        mainAxisSize = SizeMode.Expand,
        mainAxisAlignment = FlowMainAxisAlignment.Start,
        modifier = modifier.fillMaxWidth()
    ) {

        val modifierItem = Modifier
            .width(itemWidthSize.dp)
            .height(itemHeightSize.dp)
            .padding(horizontal = 8.dp, vertical = 12.dp)

        if (isLoading){
            listOf(1,1,1,1,1,1).forEachIndexed{ _, _ ->
                CatalogMovieItemShimmer(modifierItem)
            }
        }else{
            moviesList?.forEachIndexed { index, _ ->
                CatalogMovieItem(
                    moviesList[index],
                    modifierItem,
                    onItemSelected = onItemSelected
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryGridPreview() {
    //CategoryGrid(Modifier.padding(horizontal = 16.dp),listOf(1, 2, 3, 4, 5, 7))
}