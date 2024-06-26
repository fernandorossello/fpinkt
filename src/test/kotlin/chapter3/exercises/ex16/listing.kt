package chapter3.exercises.ex16

import chapter3.Cons
import chapter3.List
import chapter3.foldLeft
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

// tag::init[]
fun doubleToString(xs: List<Double>): List<String> =
foldRight(xs, List.empty()) { d: Double, ls :List<String> -> Cons(d.toString(),ls) }
//foldLeft(xs,List.empty()) { ls: List<String>, d: Double -> Cons(d.toString(),ls) }
// end::init[]

class Exercise16 : WordSpec({
    "list doubleToString" should {
        "convert every double element to a string" {
            doubleToString(List.of(1.1, 1.2, 1.3, 1.4)) shouldBe
                List.of("1.1", "1.2", "1.3", "1.4")
        }
    }
})
