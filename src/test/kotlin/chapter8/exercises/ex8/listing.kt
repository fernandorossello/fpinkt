package chapter8.exercises.ex8

import chapter8.RNG
import chapter8.State
import chapter8.double
import utils.SOLUTION_HERE

data class Gen<A>(val sample: State<RNG, A>) {
    companion object {

        //tag::init[]
        fun <A> weighted(
            pga: Pair<Gen<A>, Double>,
            pgb: Pair<Gen<A>, Double>
        ): Gen<A> =
            doubleGen().flatMap { d:Double -> (if(pga.second <= d) pga else pgb).first }

        private fun doubleGen() :Gen<Double> = Gen(State { rng: RNG -> double(rng) })

        //end::init[]
    }

    fun <B> flatMap(f: (A) -> Gen<B>): Gen<B> =
        Gen(sample.flatMap { a -> f(a).sample })
}
