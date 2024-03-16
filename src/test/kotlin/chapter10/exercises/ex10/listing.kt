package chapter10.exercises.ex10

import chapter10.Monoid
import utils.SOLUTION_HERE

sealed class WC
data class Stub(val chars: String) : WC()
data class Part(val ls: String, val words: Int, val rs: String) : WC()

//tag::init1[]
fun wcMonoid(): Monoid<WC> = object : Monoid<WC> {
    override fun combine(a1: WC, a2: WC): WC =
        when(a1) {
            is Part -> when(a2) {
                is Part -> Part(a1.ls, a1.words+a2.words+1,a2.rs)
                is Stub -> Part(a1.ls, a1.words,a1.rs+a2.chars)
            }
            is Stub -> when(a2) {
                is Part -> Part(a1.chars+a2.ls, a2.words,a2.rs)
                is Stub -> Stub(a1.chars+a2.chars)
            }
        }

    override val nil: WC
        get() = Stub("")
}

//end::init1[]
