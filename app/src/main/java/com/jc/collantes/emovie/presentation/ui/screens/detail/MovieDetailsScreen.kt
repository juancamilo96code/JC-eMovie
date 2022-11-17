package com.jc.collantes.emovie.presentation.ui.screens.detail

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jc.collantes.emovie.R
import com.jc.collantes.emovie.data.model.ui.MovieDetails
import com.jc.collantes.emovie.presentation.ui.composables.*
import com.jc.collantes.emovie.utils.ImageSizer
import coil.compose.rememberAsyncImagePainter
import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.MovieVideo
import com.jc.collantes.emovie.utils.Utils

@Composable
fun MovieDetailsScreen(
    movieDetailsViewModel: MovieDetailsViewModel,
    onTrailerButtonPressed: (String) -> Unit,
    onBackButtonPressed: () -> Unit
) {

    val movieDetailsResource: Resource<MovieDetails?> by movieDetailsViewModel.movieDetailsResource.observeAsState(
        Resource.Sleep()
    )

    val movieTrailerResource: Resource<MovieVideo?> by movieDetailsViewModel.movieTrailer.observeAsState(
        Resource.Sleep()
    )

    if (movieDetailsResource is Resource.GenericDataError) {

        EmptyState(popBackEnable =  false,
            onBackButtonPressed = onBackButtonPressed,
            onRetryButton = { movieDetailsViewModel.loadData() }
        )

    } else {

        val orientation = LocalConfiguration.current.orientation

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Box(Modifier.fillMaxSize().background(Color.Black, RectangleShape)) {
                if (movieDetailsResource is Resource.Success) {
                    Image(
                        painter = rememberAsyncImagePainter(model = stringResource(id = R.string.themoviedb_base_images_url) + movieDetailsResource.data?.singleMovieItemData?.imagePath),
                        contentDescription = movieDetailsResource.data?.singleMovieItemData?.name,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Column(Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Black, Color.Transparent
                                    )
                                )
                            )
                            .weight(1f)
                            .fillMaxWidth()
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "arrow_back",
                            modifier = Modifier
                                .padding(top = 38.dp, start = 28.dp)
                                .clickable(true) {
                                    onBackButtonPressed()
                                }
                        )
                    }
                    Box(
                        modifier = Modifier
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent, Color.Black

                                    )
                                )
                            )
                            .weight(1f)
                            .fillMaxWidth()
                    )
                }

            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp,
                        top = 100.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(((LocalConfiguration.current.screenHeightDp * 0.625f)-100).dp))
                }
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        movieDetailsResource.data?.singleMovieItemData?.name?.let {
                            Title(
                                text = it, modifier = Modifier.align(Alignment.CenterHorizontally),
                                isLoading = (movieDetailsResource is Resource.Loading)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                            movieDetailsResource.data?.releaseDate?.let {
                                TagButton(
                                    text = Utils.getYearOfDate(it),
                                    Modifier
                                        .fillMaxHeight()
                                        .padding(end = 8.dp),
                                    isLoading = (movieDetailsResource is Resource.Loading)
                                )
                            }
                            movieDetailsResource.data?.originalLanguage?.let {
                                TagButton(
                                    text = it,
                                    Modifier
                                        .fillMaxHeight()
                                        .padding(end = 8.dp),
                                    isLoading = (movieDetailsResource is Resource.Loading)
                                )
                            }
                            movieDetailsResource.data?.voteAverage?.let {
                                RatingButton(
                                    rate = Utils.formatRate(it), isEnable = true, maxRange = 10f,
                                    isLoading = (movieDetailsResource is Resource.Loading)
                                )
                            }
                        }

                        if (movieDetailsResource is Resource.Loading) {
                            Spacer(modifier = Modifier.height(16.dp))
                            LabelText(
                                text = "genresList", modifier = Modifier, isLoading = true
                            )
                        } else if (movieDetailsResource is Resource.Success) {
                            Spacer(modifier = Modifier.height(16.dp))
                            LazyRow(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                                val genresList = movieDetailsResource.data?.genres?.map {
                                    it.name
                                }
                                if (genresList != null) {
                                    if (genresList.isNotEmpty()) {
                                        item {
                                            genresList[0]?.let {
                                                LabelText(
                                                    text = it, modifier = Modifier
                                                )
                                            }
                                        }
                                        if (genresList.size > 1) {
                                            items(genresList.subList(1, genresList.size - 1)) {
                                                Row {
                                                    Icon(
                                                        painter = painterResource(id = R.drawable.ic_ellipse_separator),
                                                        contentDescription = "o",
                                                        modifier = Modifier
                                                            .padding(horizontal = 8.dp)
                                                            .align(Alignment.CenterVertically)
                                                    )
                                                    if (it != null) {
                                                        LabelText(it, modifier = Modifier)
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
                if (!(movieTrailerResource is Resource.Success && movieTrailerResource.data == null)
                    && movieTrailerResource !is Resource.GenericDataError) {
                    item {
                        val baseUrl = stringResource(id = R.string.youtube_url)
                        Spacer(modifier = Modifier.height(16.dp))
                        OutLineTransparentActionButton(
                            stringResource(id = R.string.watch_trailer),
                            Modifier
                                .fillMaxWidth(),
                            isLoading = (!(movieTrailerResource is Resource.Success
                                    && movieDetailsResource is Resource.Success)),
                            onClicked = {
                                movieTrailerResource.data?.key?.let { onTrailerButtonPressed(baseUrl + it) }
                            })

                        Spacer(modifier = Modifier.height(16.dp))
                    }

                }

                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        SubTitle3(
                            text = movieDetailsResource.data?.tagline,
                            modifier = Modifier.align(Alignment.Start),
                            isLoading = (movieDetailsResource is Resource.Loading)
                        )
                        ContentText(
                            text = movieDetailsResource.data?.overview,
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(bottom = 24.dp),
                            isLoading = (movieDetailsResource is Resource.Loading)
                        )
                    }
                }

            }

        }
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LazyColumn(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                item {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "arrow_back",
                        modifier = Modifier
                            .padding(top = 38.dp, start = 4.dp)
                            .clickable(true) {
                                onBackButtonPressed()
                            }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Row {
                        CatalogMovieItem(
                            movieDetailsResource.data?.singleMovieItemData,
                            Modifier
                                .width(200.dp)
                                .height(ImageSizer().getHeightByWidth(200f).dp)
                                .padding(horizontal = 8.dp, vertical = 12.dp),
                            isLoading = (movieDetailsResource is Resource.Loading)
                        ) {}
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 24.dp)
                                .align(Alignment.Bottom)
                        ) {
                            Title(
                                text = movieDetailsResource.data?.singleMovieItemData?.name,
                                modifier = Modifier.align(Alignment.Start),
                                isLoading = (movieDetailsResource is Resource.Loading)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(modifier = Modifier.align(Alignment.Start)) {
                                movieDetailsResource.data?.releaseDate?.let {
                                    TagButton(
                                        text = Utils.getYearOfDate(it),
                                        Modifier
                                            .fillMaxHeight()
                                            .padding(end = 8.dp),
                                        isLoading = (movieDetailsResource is Resource.Loading)
                                    )
                                }

                                movieDetailsResource.data?.originalLanguage?.let {
                                    TagButton(
                                        text = it,
                                        Modifier
                                            .fillMaxHeight()
                                            .padding(end = 8.dp),
                                        isLoading = (movieDetailsResource is Resource.Loading)
                                    )
                                }
                                movieDetailsResource.data?.voteAverage?.let {
                                    RatingButton(
                                        rate = Utils.formatRate(it), isEnable = true, maxRange = 10f,
                                        isLoading = (movieDetailsResource is Resource.Loading)
                                    )
                                }
                            }
                            if (movieDetailsResource is Resource.Loading) {
                                Spacer(modifier = Modifier.height(8.dp))
                                LabelText(
                                    text = "genresList", modifier = Modifier, isLoading = true
                                )
                            } else if (movieDetailsResource is Resource.Success) {
                                Spacer(modifier = Modifier.height(8.dp))
                                LazyRow(modifier = Modifier.align(Alignment.Start)) {
                                    val genresList = movieDetailsResource.data?.genres?.map {
                                        it.name
                                    }
                                    if (genresList != null) {
                                        if (genresList.isNotEmpty()) {
                                            item {
                                                genresList[0]?.let {
                                                    LabelText(
                                                        text = it, modifier = Modifier
                                                    )
                                                }
                                            }
                                            if (genresList.size > 1) {
                                                items(genresList.subList(1, genresList.size - 1)) {
                                                    Row {
                                                        Icon(
                                                            painter = painterResource(id = R.drawable.ic_ellipse_separator),
                                                            contentDescription = "o",
                                                            modifier = Modifier
                                                                .padding(horizontal = 8.dp)
                                                                .align(Alignment.CenterVertically)
                                                        )
                                                        if (it != null) {
                                                            LabelText(it, modifier = Modifier)
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            if (!(movieTrailerResource is Resource.Success && movieTrailerResource.data == null)
                                && movieTrailerResource !is Resource.GenericDataError) {
                                val baseUrl = stringResource(id = R.string.youtube_url)
                                OutLineTransparentActionButton(
                                    stringResource(id = R.string.watch_trailer),
                                    Modifier
                                        .fillMaxWidth(),
                                    isLoading = (!(movieTrailerResource is Resource.Success
                                            && movieDetailsResource is Resource.Success)),
                                    onClicked = {
                                        movieTrailerResource.data?.key?.let {
                                            onTrailerButtonPressed(
                                                baseUrl + it
                                            )
                                        }
                                    })
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        SubTitle3(
                            text = movieDetailsResource.data?.tagline,
                            modifier = Modifier.align(Alignment.Start),
                            isLoading = (movieDetailsResource is Resource.Loading)
                        )
                        ContentText(
                            text = movieDetailsResource.data?.overview,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(bottom = 24.dp),
                            isLoading = (movieDetailsResource is Resource.Loading)
                        )
                    }
                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenPreview() {
    //MovieDetailsScreen(MovieDetailsViewModel())
}