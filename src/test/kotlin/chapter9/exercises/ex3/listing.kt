package chapter9.exercises.ex3

import chapter9.solutions.final.ParseError
import chapter9.solutions.final.Parser
import chapter9.solutions.final.ParserDsl
import utils.SOLUTION_HERE

abstract class Listing : ParserDsl<ParseError>() {

    /*
     Before continuing, see if you can define many in terms of or, map2, and succeed.
     */

    fun <A, B, C> map2(
        pa: Parser<A>,
        pb: Parser<B>,
        f: (A, B) -> C
    ): Parser<C> =
        (pa product {pb}).map { (a:A,b:B) -> f(a,b) }

    init {
        //tag::init1[]
        // zero or more
        fun <A> many(pa: Parser<A>): Parser<List<A>> =
            map2(pa, many(pa)) {a: A,b:List<A> -> (listOf(a) + b) } or succeed(
                emptyList())

        //end::init1[]
    }
}
