package chapter10.exercises.ex2

import arrow.core.None
import arrow.core.Option
import arrow.core.orElse
import chapter10.Monoid
import utils.SOLUTION_HERE

//tag::init1[]
fun <A> optionMonoid(): Monoid<Option<A>> = object : Monoid<Option<A>> {
    override fun combine(a1: Option<A>, a2: Option<A>): Option<A> =
        a1.orElse { a2 }

    override val nil: Option<A>
        get() = None
}

//end::init1[]

//tag::init2[]
fun <A> dual(m: Monoid<A>): Monoid<A> = object : Monoid<A> {
    override fun combine(a1: A, a2: A): A =
        m.combine(a1,a2)

    override val nil: A
        get() = m.nil
}

//end::init2[]

fun <A> firstOptionMonoid() = optionMonoid<A>()

fun <A> lastOptionMonoid() = dual(firstOptionMonoid<A>())
