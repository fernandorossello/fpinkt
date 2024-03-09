package chapter9.exercises.ex8

import chapter9.solutions.final.Example.flatMap
import chapter9.solutions.final.ParseError
import chapter9.solutions.final.Parser
import chapter9.solutions.final.ParserDsl
import utils.SOLUTION_HERE

abstract class Listing : ParserDsl<ParseError>() {

    init {
        //tag::init1[]
        fun <A, B> map(pa: Parser<A>, f: (A) -> B): Parser<B> =
            pa.flatMap { succeed(f(it)) }
        //end::init1[]
    }
}
