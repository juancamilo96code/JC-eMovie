package com.jc.collantes.emovie.presentation.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.jc.collantes.emovie.data.model.ui.Routes
import com.jc.collantes.emovie.presentation.ui.screens.detail.MovieDetailsScreen
import com.jc.collantes.emovie.presentation.ui.screens.detail.MovieDetailsViewModel
import com.jc.collantes.emovie.presentation.ui.screens.home.HomeMoviesCatalogScreen
import com.jc.collantes.emovie.presentation.ui.screens.home.HomeMoviesCatalogViewModel
import com.jc.collantes.emovie.presentation.ui.screens.splash.SplashScreen
import com.jc.collantes.emovie.presentation.ui.theme.EMovieTheme
import dagger.hilt.android.AndroidEntryPoint
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController!!.hide(
                android.view.WindowInsets.Type.statusBars()
            )
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            actionBar?.hide()
        }
        setContent {
            EMovieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(navController = navController, startDestination = Routes.Splash.route) {

                        composable(
                            Routes.Splash.route,
                            exitTransition = {
                                fadeOut(animationSpec = tween(500))
                            }
                        ) {
                            SplashScreen(navController)
                        }
                        composable(
                            Routes.HomeMoviesCatalog.route,
                            enterTransition = {
                                fadeIn(animationSpec = tween(500))
                            },
                            popEnterTransition = {
                                fadeIn(animationSpec = tween(500))
                            },
                            exitTransition = {
                                slideOutHorizontally (
                                    targetOffsetX = {-300},
                                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                                        )+
                                        fadeOut(animationSpec = tween(500))
                            }
                        ) {
                            val homeMoviesCatalogViewModel by viewModels<HomeMoviesCatalogViewModel>()
                            HomeMoviesCatalogScreen(homeMoviesCatalogViewModel = homeMoviesCatalogViewModel) {
                                navController.navigate(
                                    Routes.MovieDetails.route.replace(
                                        oldValue = "{movieId}",
                                        newValue = it.toString()
                                    ),
                                )
                            }
                        }

                        composable(Routes.MovieDetails.route,
                            enterTransition = {
                                fadeIn(animationSpec = tween(300))
                            },
                            popExitTransition = {
                                fadeOut(animationSpec = tween(300))
                            }
                        ) {
                            val movieDetailsViewModel = hiltViewModel<MovieDetailsViewModel> ()
                            MovieDetailsScreen(
                                movieDetailsViewModel = movieDetailsViewModel,
                                onTrailerButtonPressed = {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    intent.setPackage("com.google.android.youtube")
                                    startActivity(intent)
                                },
                                onBackButtonPressed = {
                                    navController.popBackStack()
                                })
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        EMovieTheme {

        }
    }

}