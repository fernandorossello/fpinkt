package chapter10.exercises.ex5

import arrow.core.extensions.list.foldable.fold
import arrow.core.extensions.list.foldable.foldLeft
import chapter10.sec1.Monoid
import utils.SOLUTION_HERE

//tag::init1[]
fun <A, B> foldMap(la: List<A>, m: Monoid<B>, f: (A) -> B): B =
    la.foldLeft(m.nil) {b:B, a:A -> m.combine(b,f(a)) }
//end::init1[]
