package chapter6.exercises.ex7

// import chapter3.Cons
import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter3.foldRight
// import chapter3.Nil
// import chapter3.solutions.foldRight
import chapter6.RNG
import chapter6.Rand
import chapter6.rng1
import chapter6.solutions.ex6.map2
// import chapter6.solutions.ex6.map2
import chapter6.unit
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

class Exercise7 : WordSpec({

    //tag::init[]
    // typealias Rand<A> = (RNG) -> Pair<A, RNG>
    fun <A> sequence(fs: List<Rand<A>>): Rand<List<A>> = { rng ->
        when (fs) {
            is Nil -> unit(List.empty<A>())(rng)
            is Cons -> {
                val (h: A, rn1: RNG) = fs.head(rng)
                val (t: List<A>, rn2: RNG) = sequence(fs.tail)(rn1)
                Cons(h, t) to rn2
            }
        }
    }
    //end::init[]

    //tag::init2[]

    // fun <A, B> foldRight(xs: List<A>, z: B, f: (A, B) -> B)
    fun <A> sequence2(fs: List<Rand<A>>): Rand<List<A>> =
        foldRight(fs, unit(List.empty<A>())) { r:Rand<A>, lr: Rand<List<A>> ->
            map2(r,lr) {h, t -> Cons(h,t)}
        }

    fun ints2(count: Int, rng: RNG): Pair<List<Int>, RNG> = SOLUTION_HERE()

    "sequence" should {

        "combine the results of many actions using recursion" {

            val combined: Rand<List<Int>> =
                sequence(
                    List.of(
                        unit(1),
                        unit(2),
                        unit(3),
                        unit(4)
                    )
                )

            combined(rng1).first shouldBe
                List.of(1, 2, 3, 4)
        }

        """combine the results of many actions using
            foldRight and map2""" {

            val combined2: Rand<List<Int>> =
                sequence2(
                    List.of(
                        unit(1),
                        unit(2),
                        unit(3),
                        unit(4)
                    )
                )

            combined2(rng1).first shouldBe
                List.of(1, 2, 3, 4)
        }
    }

    "ints" should {
        "!generate a list of ints of a specified length" {
            ints2(4, rng1).first shouldBe
                List.of(1, 1, 1, 1)
        }
    }
})
