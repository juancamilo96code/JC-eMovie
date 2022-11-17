package com.jc.collantes.emovie.presentation.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jc.collantes.emovie.R
import coil.compose.rememberAsyncImagePainter
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem

@Composable
fun CatalogMovieItem(item: SimpleMovieItem?, modifier: Modifier, isLoading : Boolean = false,onItemSelected: (Long) -> Unit) {

    if (isLoading){
        CatalogMovieItemShimmer(modifier = modifier)
    }else{
        Image(
            painter = rememberAsyncImagePainter(stringResource(id = R.string.themoviedb_base_images_url)+item?.imagePath),
            contentDescription = item?.name,
            contentScale = ContentScale.FillWidth,
            modifier = modifier
                .clip(RoundedCornerShape(20f))
                .clickable { item?.id?.let { onItemSelected(it) } }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun CatalogMovieItemPreview() {
    val item = SimpleMovieItem(1,"hola","")
    CatalogMovieItem(item, Modifier.width(138.dp)) {

    }
}