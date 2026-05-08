package io.kotest.property.arbs.movies

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.map
import io.kotest.property.arbs.generated.movies.moviesMoviesData
import io.kotest.property.arbs.generated.movies.moviesRolesData
import io.kotest.property.arbs.generated.movies.moviesStarsData

data class MovieStar(val name: String)
data class Movie(val title: String)
data class MovieRole(val name: String)
data class MovieStarRole(val star: MovieStar, val movie: Movie, val role: MovieRole)

fun Arb.Companion.movieStar() = arbitrary { MovieStar(moviesStarsData.random(it.random)) }
fun Arb.Companion.movie() = arbitrary { Movie(moviesMoviesData.random(it.random)) }
fun Arb.Companion.movieRole() = arbitrary { MovieRole(moviesRolesData.random(it.random)) }

fun Arb.Companion.movieStarRole() =
  movieStar().flatMap { star ->
    movie().flatMap { movie ->
      movieRole().map { role -> MovieStarRole(star, movie, role) }
    }
  }
