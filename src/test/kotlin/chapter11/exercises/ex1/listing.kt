package chapter11.exercises.ex1

import arrow.Kind
import arrow.core.ForListK
import arrow.core.ForSequenceK
import chapter10.ForList
import chapter10.ForOption
import chapter10.fix
import chapter11.ForPar
import chapter11.Par
import chapter10.Some
import chapter11.fix
import chapter11.sec2.Monad
import utils.SOLUTION_HERE

//tag::init1[]
object Monads {

    fun parMonad(): Monad<ForPar> = object : Monad<ForPar> {
        override fun <A> unit(a: A): Kind<ForPar, A> =
            Par.unit(a)


        override fun <A, B> flatMap(
            fa: Kind<ForPar, A>,
            f: (A) -> Kind<ForPar, B>
        ): Kind<ForPar, B> =
            fa.fix().flatMap { f(it).fix() }
    }


    fun optionMonad(): Monad<ForOption> = object : Monad<ForOption> {
        override fun <A> unit(a: A): Kind<ForOption, A> =
            Some(a)

        override fun <A, B> flatMap(
            fa: Kind<ForOption, A>,
            f: (A) -> Kind<ForOption, B>
        ): Kind<ForOption, B> =
            fa.fix().flatMap { f(it).fix() }
    }

    fun listMonad(): Monad<ForList> =

        SOLUTION_HERE()

    fun listKMonad(): Monad<ForListK> =

        SOLUTION_HERE()

    fun sequenceKMonad(): Monad<ForSequenceK> =

        SOLUTION_HERE()
}
//end::init1[]
