package com.jc.collantes.emovie.data.model.ui

sealed class Routes(val route: String){
    object Splash:Routes("Splash")
    object HomeMoviesCatalog:Routes("HomeMoviesCatalog")
    object MovieDetails:Routes("MovieDetails/{movieId}")
    //https://api.themoviedb.org/3/movie/239?api_key=175a7356eb593bbb3031e5bdd8f99903&language=es-CO
    //https://api.themoviedb.org/3/movie/upcoming?api_key=175a7356eb593bbb3031e5bdd8f99903
    //https://api.themoviedb.org/3/movie/top_rated?api_key=175a7356eb593bbb3031e5bdd8f99903
    //https://image.tmdb.org/t/p/w500/iSbhQqeDnkvFuP7QWwy0qeX0LYy.jpg
}