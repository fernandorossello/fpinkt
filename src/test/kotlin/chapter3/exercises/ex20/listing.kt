package chapter3.exercises.ex20

import chapter3.List
import chapter3.flatMap
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

// tag::init[]
fun <A> filter2(xa: List<A>, f: (A) -> Boolean): List<A> =
    flatMap(xa) {e -> if(f(e)) List.of(e) else List.empty() }
// end::init[]
// fun <A, B> flatMap(xa: List<A>, f: (A) -> List<B>): List<B> =

class Exercise20 : WordSpec({
    "list filter" should {
        "filter out elements not compliant to predicate" {
            filter2(
                List.of(1, 2, 3, 4, 5)
            ) { it % 2 == 0 } shouldBe List.of(2, 4)
        }
    }
})
