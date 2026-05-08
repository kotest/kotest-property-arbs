package io.kotest.property.arbs.movies

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll

class MovieStarsTest : FunSpec() {
  init {
    test("movieStar emits a non-blank name") {
      checkAll(20, Arb.movieStar()) { it.name.isNotBlank() shouldBe true }
    }
    test("movie emits a non-blank title") {
      checkAll(20, Arb.movie()) { it.title.isNotBlank() shouldBe true }
    }
    test("movieRole emits a non-blank role") {
      checkAll(20, Arb.movieRole()) { it.name.isNotBlank() shouldBe true }
    }
    test("movieStarRole composes star, movie, and role") {
      checkAll(5, Arb.movieStarRole()) { sr ->
        sr.star.name.isNotBlank() shouldBe true
        sr.movie.title.isNotBlank() shouldBe true
        sr.role.name.isNotBlank() shouldBe true
      }
    }
  }
}
