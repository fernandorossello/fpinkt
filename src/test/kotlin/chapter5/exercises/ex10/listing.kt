package chapter5.exercises.ex10

import chapter3.List
import chapter5.Empty
import chapter5.Stream
import chapter5.Stream.Companion.cons
import chapter5.Stream.Companion.empty
import chapter5.solutions.ex13.take
import chapter5.solutions.ex7.append
import chapter5.toList
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

class Exercise10 : WordSpec({
    //tag::init[]
    fun fibs(): Stream<Int> {
        fun go(a: Int, b: Int): Stream<Int> =
            cons({ a }) { go(b, a + b) }

        return go(0, 1)
    }

    //end::init[]

    "fibs" should {
        "return a Stream of fibonacci sequence numbers" {
            fibs().take(10).toList() shouldBe
                List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34)
        }
    }
})
