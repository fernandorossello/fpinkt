package chapter8.exercises.ex13

import arrow.core.extensions.list.foldable.exists
import chapter8.Gen
import chapter8.Prop
import chapter8.Prop.Companion.forAll
import chapter8.RNG
import chapter8.SGen
import chapter8.State
import chapter8.sec4_1.run
import utils.SOLUTION_HERE
import kotlin.math.max

fun main() {
    //tag::init1[]
    fun <A> nonEmptyListOf(ga: Gen<A>): SGen<List<A>> =
        SGen { size:Int -> Gen.listOfN(max(1,size), ga) }
    //end::init1[]

    val smallInt = Gen.choose(-10, 10)

    //tag::init2[]
    fun maxProp(): Prop =
        forAll(nonEmptyListOf(smallInt)) { ns ->
            val mx = ns.max()
            ?: throw IllegalStateException("max on empty list")
        !ns.exists { it > mx }
    }

    //end::init2[]
    run(maxProp())
}
