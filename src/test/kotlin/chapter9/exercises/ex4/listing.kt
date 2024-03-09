package chapter9.exercises.ex4

import chapter9.solutions.final.ParseError
import chapter9.solutions.final.Parser
import chapter9.solutions.final.ParserDsl
import utils.SOLUTION_HERE

abstract class Listing : ParserDsl<ParseError>() {

    /*
    Implement the listOfN combinator introduced earlier using map2 and succeed.
     */

    fun <A, B, C> map2(
        pa: Parser<A>,
        pb: Parser<B>,
        f: (A, B) -> C
    ): Parser<C> = (pa product {pb}).map { (a:A,b:B) -> f(a,b) }

    init {
        //tag::init1[]
        fun <A> listOfN(n: Int, pa: Parser<A>): Parser<List<A>> =
            if(n > 0) {
                map2(pa, listOfN(n-1,pa)) { a,la -> listOf(a) + la } }
            else {
                succeed(emptyList())
            }
        //end::init1[]
    }
}
