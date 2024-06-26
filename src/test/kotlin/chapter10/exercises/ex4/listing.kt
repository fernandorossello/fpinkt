package chapter10.exercises.ex4

import chapter10.Monoid
import chapter10.intAdditionMonoid
import chapter10.intMultiplicationMonoid
import chapter8.Passed
import chapter8.SimpleRNG
import chapter8.Gen
import chapter8.Prop
import chapter8.sec4_11.combine
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

//tag::init1[]
fun <A> monoidLaws(m: Monoid<A>, gen: Gen<A>): Prop =
    Prop.Companion.forAll(
        gen.flatMap { a1:A ->
            gen.flatMap {a2:A ->
                gen.map { a3:A -> Triple(a1,a2,a3) }
            }
        }) { a:Triple<A,A,A> ->
        m.combine(a.first, m.combine(a.second,a.third)) == m.combine(m.combine(a.first,a.second),a.third)
    }
//end::init1[]

//tag::init2[]
class Exercise4 : WordSpec({
    val max = 100
    val count = 100
    val rng = SimpleRNG(42)
    val intGen = Gen.choose(-10000, 10000)

    "law of associativity" should {
        "be upheld using existing monoids" {
            monoidLaws(intAdditionMonoid, intGen)
                .check(max, count, rng) shouldBe Passed

            monoidLaws(intMultiplicationMonoid, intGen)
                .check(max, count, rng) shouldBe Passed
        }
    }
})
//end::init2[]
