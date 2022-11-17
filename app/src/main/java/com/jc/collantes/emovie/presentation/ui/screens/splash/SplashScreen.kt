package com.jc.collantes.emovie.presentation.ui.screens.splash


import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jc.collantes.emovie.data.model.ui.Routes
import com.jc.collantes.emovie.presentation.ui.composables.Logo
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController? = null) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Black,
                        Color.Black,
                        Color(0xFF800606)
                    )
                )
            )
            .fillMaxSize()
    ) {

        val launchAnimation = remember { mutableStateOf(false) }
        val logoSize by animateDpAsState(targetValue = if (launchAnimation.value)350.dp else 100.dp,
            animationSpec = tween(3000),
            finishedListener = {
                navController?.popBackStack()
                navController?.navigate(Routes.HomeMoviesCatalog.route)
                launchAnimation.value = false
            }
        )
        Logo(
            Modifier
                .align(Alignment.Center)
                .padding(horizontal = 100.dp).size(logoSize))
        LaunchedEffect(key1 = true) {
            launchAnimation.value = true
        }



    }

}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}