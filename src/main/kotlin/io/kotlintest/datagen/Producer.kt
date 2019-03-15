package io.kotlintest.datagen

/**
 * Instances of [Producer] are used to produce data for testing.
 */
interface Producer<A> {
  fun produce(): A

  /**
   * Returns a new [Producer] which produces data by first invoking this producer,
   * and then mapping the values with the given function.
   */
  fun <B> map(f: (A) -> B): Producer<B> = object : Producer<B> {
    override fun produce(): B = f(this@Producer.produce())
  }

  /**
   * Returns a new [Producer] which produces elements by mapping tuples of values
   * produced by this producer and the given producer.
   */
  fun <B, R> zip(pb: Producer<B>, f: (A, B) -> R): Producer<R> = object : Producer<R> {
    override fun produce(): R = f(this@Producer.produce(), pb.produce())
  }

  /**
   * Returns a new [Producer] which produces elements by mapping tuples of values
   * produced by this producer and the given producer.
   */
  fun <B, C, R> zip(pb: Producer<B>, pc: Producer<C>, f: (A, B, C) -> R): Producer<R> = object : Producer<R> {
    override fun produce(): R = f(this@Producer.produce(), pb.produce(), pc.produce())
  }

  /**
   * Returns a new [Producer] which produces elements by mapping tuples of values
   * produced by this producer and the given producer.
   */
  fun <B, C, D, R> zip(pb: Producer<B>,
                       pc: Producer<C>,
                       pd: Producer<D>,
                       f: (A, B, C, D) -> R): Producer<R> = object : Producer<R> {
    override fun produce(): R = f(this@Producer.produce(), pb.produce(), pc.produce(), pd.produce())
  }

  /**
   * Returns a new [Producer] which produces elements by mapping tuples of values
   * produced by this producer and the given producer.
   */
  fun <B, C, D, E, R> zip(pb: Producer<B>,
                          pc: Producer<C>,
                          pd: Producer<D>,
                          pe: Producer<E>,
                          f: (A, B, C, D, E) -> R): Producer<R> = object : Producer<R> {
    override fun produce(): R = f(this@Producer.produce(), pb.produce(), pc.produce(), pd.produce(), pe.produce())
  }
}