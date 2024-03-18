package chapter11.exercises.ex3

import arrow.Kind
import chapter10.Cons
import chapter10.List
import chapter11.Functor
import chapter11.sec1.map
import utils.SOLUTION_HERE

interface Monad<F> : Functor<F> {

    fun <A> unit(a: A): Kind<F, A>
    fun <A, B> flatMap(fa: Kind<F, A>, f: (A) -> Kind<F, B>): Kind<F, B>

    override fun <A, B> map(fa: Kind<F, A>, f: (A) -> B): Kind<F, B> =
        flatMap(fa) { a -> unit(f(a)) }

    fun <A, B, C> map2(fa: Kind<F, A>, fb: Kind<F, B>, f: (A, B) -> C) =
        flatMap(fa) { a -> map(fb) { b -> f(a, b) } }

    //tag::traverse[]
    fun <A, B> traverse(
        la: List<A>,
        f: (A) -> Kind<F, B>
    ): Kind<F, List<B>> =
        la.foldRight(unit(List.empty<B>())) { el: A, ac: Kind<F, List<B>> ->
            map2(ac, f(el)) { lb: List<B>, b:B -> Cons(b,lb) }
        }
    //end::traverse[]

    //tag::sequence[]
    fun <A> sequence(lfa: List<Kind<F, A>>): Kind<F, List<A>> =
        lfa.foldLeft(unit(List.empty<A>())) { kla: Kind<F, List<A>>, ka: Kind<F, A> ->
            map2(kla,ka) { la: List<A>, a: A -> Cons(a,la) }
        }

    //end::sequence[]
}
