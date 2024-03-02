package chapter8.exercises.ex6

import chapter8.RNG
import chapter8.State
import utils.SOLUTION_HERE

//tag::init[]
data class Gen<A>(val sample: State<RNG, A>) {

    companion object {
        fun <A> listOfN(gn: Gen<Int>, ga: Gen<A>): Gen<List<A>> =
            gn.flatMap { n:Int -> Gen(State.sequence(List(n) {ga.sample})) }
    }

    fun <B> flatMap(f: (A) -> Gen<B>): Gen<B> =
        Gen(sample.flatMap { a-> f(a).sample })
}
//end::init[]
