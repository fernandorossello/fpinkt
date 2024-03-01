package chapter8.exercises.ex4

import chapter8.RNG
import chapter8.State

data class Gen<A>(val sample: State<RNG, A>) {
    companion object {
        //tag::init[]
        fun choose(start: Int, stopExclusive: Int): Gen<Int> =
            Gen(State {rng:RNG ->
                rng.nextIntBetween(start,stopExclusive)
            })
    }
}

// I know its not performant
private fun RNG.nextIntBetween(start: Int, stopExclusive: Int) : Pair<Int,RNG> {
    val (i,rng2) = this.nextInt()
    return if(i in start until stopExclusive) {
        i to rng2
    } else {
        this.nextIntBetween(start,stopExclusive)
    }
}
