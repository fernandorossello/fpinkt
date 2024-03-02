package chapter8.exercises.ex5

import chapter8.RNG
import chapter8.State
import chapter8.nextBoolean
import utils.SOLUTION_HERE

data class Gen<A>(val sample: State<RNG, A>) {
    companion object {

        //tag::init[]
        fun <A> unit(a: A): Gen<A> =
            Gen(State { rng:RNG -> a to rng })

        fun boolean(): Gen<Boolean> =
            Gen(State { rng:RNG -> nextBoolean(rng) })


        fun <A> listOfN(n: Int, ga: Gen<A>): Gen<List<A>> =
            Gen(State.sequence(List(n) { ga.sample}))

        //end::init[]
    }
}
