package chapter3.exercises.ex5

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter4.size
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

// tag::init[]
tailrec fun <A> init(l: List<A>): List<A> {
    return when(l) {
        is Nil -> throw IllegalStateException()
        is Cons -> if(l.size() == 1) Nil else Cons(l.head,init(l.tail))
    }
}

// end::init[]

class Exercise5 : WordSpec({

    "list init" should {
        "return all but the last element" {
            init(List.of(1, 2, 3, 4, 5)) shouldBe
                List.of(1, 2, 3, 4)
        }

        "return Nil if only one element exists" {
            init(List.of(1)) shouldBe Nil
        }

        "throw an exception if no elements exist" {
            shouldThrow<IllegalStateException> {
                init(List.empty<Int>())
            }
        }
    }
})
