package chapter2.exercises.ex1

import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import kotlinx.collections.immutable.persistentMapOf
import utils.SOLUTION_HERE

class Exercise1 : WordSpec({

    fun fib(i: Int): Int {
        tailrec fun go(i: Int, prev: Int, current: Int, upTo: Int): Int =
            if (upTo <= i) {
                prev + current
            } else {
                go(i + 1, current, prev + current, upTo)
            }

        return go(2, 0, 1, i)
    }

    "fib" should {
        "return the nth fibonacci number" {
            persistentMapOf(
                1 to 1,
                2 to 1,
                3 to 2,
                4 to 3,
                5 to 5,
                6 to 8,
                7 to 13,
                8 to 21
            ).forEach { (n, num) ->
                fib(n) shouldBe num
            }
        }
    }
})
