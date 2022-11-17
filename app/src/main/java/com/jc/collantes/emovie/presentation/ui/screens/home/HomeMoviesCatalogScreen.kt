package com.jc.collantes.emovie.presentation.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.CustomChipData
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem
import com.jc.collantes.emovie.presentation.ui.composables.*
import com.jc.collantes.emovie.R

@Composable
fun HomeMoviesCatalogScreen(
    homeMoviesCatalogViewModel: HomeMoviesCatalogViewModel,
    onItemSelected: (Long) -> Unit
) {

    val upcomingListResource: Resource<List<SimpleMovieItem>> by homeMoviesCatalogViewModel.upcomingList.observeAsState(
        initial = Resource.Sleep()
    )
    val topRatedListResource: Resource<List<SimpleMovieItem>> by homeMoviesCatalogViewModel.topRatedList.observeAsState(
        initial = Resource.Sleep()
    )
    val recommendationListResource: Resource<List<SimpleMovieItem>> by homeMoviesCatalogViewModel.recommendationList.observeAsState(
        initial = Resource.Sleep()
    )

    val filterList: List<CustomChipData> by homeMoviesCatalogViewModel.filterList.observeAsState(
        initial = listOf()
    )

    val emptyState: Boolean by homeMoviesCatalogViewModel.emptyState.observeAsState(
        initial = false
    )

    if(emptyState){
        EmptyState(popBackEnable =  false,
            {},{
                homeMoviesCatalogViewModel.loadData()
            }
        )
    }else{
        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Image(painter = painterResource(R.drawable.ic_logo),
                        contentDescription = "eMovie",
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .height(32.dp)
                    )
                }
            }

            if (upcomingListResource !is Resource.GenericDataError) {
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                    SubTitle(
                        text = stringResource(id = R.string.upcoming_category_title),
                        Modifier.padding(start = 24.dp),
                        isLoading = (upcomingListResource is Resource.Loading)
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                    )
                    CategoryCarousel(
                        Modifier.padding(start = 24.dp),
                        upcomingListResource.data,
                        isLoading = (upcomingListResource is Resource.Loading),
                        onItemSelected = onItemSelected
                    )
                }

            }

            if (topRatedListResource !is Resource.GenericDataError) {
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                    SubTitle(
                        text = stringResource(id = R.string.toprated_category_title),
                        Modifier.padding(start = 24.dp),
                        isLoading = (topRatedListResource is Resource.Loading)
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                    )
                    CategoryCarousel(
                        Modifier.padding(start = 24.dp),
                        topRatedListResource.data,
                        isLoading = (topRatedListResource is Resource.Loading),
                        onItemSelected = onItemSelected
                    )
                }
            }

            if (recommendationListResource !is Resource.GenericDataError) {
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                    SubTitle(
                        text = stringResource(id = R.string.recommended_category_title),
                        Modifier.padding(start = 24.dp),
                        isLoading = (recommendationListResource is Resource.Loading)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    filterList.forEach {
                        var isActive by rememberSaveable {
                            mutableStateOf(false)
                        }
                        it.isActive = isActive
                        it.onCheckedChange = { newStatus ->
                            isActive = newStatus
                            it.isActive = isActive
                            homeMoviesCatalogViewModel.filterRecommendedList()
                        }
                    }
                    GroupChips(
                        modifier = Modifier.padding(start = 24.dp),
                        customChipsList = filterList,
                        isLoading = (recommendationListResource is Resource.Loading)
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                    )
                    CategoryGrid(
                        Modifier.padding(horizontal = 16.dp),
                        recommendationListResource.data,
                        isLoading = (recommendationListResource is Resource.Loading),
                        onItemSelected = onItemSelected
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeMoviesCatalogScreenPreview() {
    //HomeMoviesCatalogScreen(HomeMoviesCatalogViewModel())
}