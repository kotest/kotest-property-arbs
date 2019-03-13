package io.kotlintest.datagen

/**
 * Instances of [Producer] are used to produce data for testing.
 */
interface Producer<T> {
  fun produce(): T
}