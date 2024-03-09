package chapter9.exercises.ex6

import chapter9.solutions.final.ParseError
import chapter9.solutions.final.Parser
import chapter9.solutions.final.ParserDsl
import utils.SOLUTION_HERE

abstract class Listing : ParserDsl<ParseError>() {
    init {

        //tag::init1[]
        val parser: Parser<Int> =
            regex("[0-9]+").flatMap { s:String -> listOfN(s[0].toInt(),char('a')).slice().map { it.length } }
        //end::init1[]
    }
}
