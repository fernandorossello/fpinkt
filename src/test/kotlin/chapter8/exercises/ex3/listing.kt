package chapter8.exercises.ex3

import utils.SOLUTION_HERE

//tag::init[]
interface Prop {
    fun check(): Boolean
    fun and(p: Prop): Prop {
        val checked = check() && p.check()
           return object : Prop {
                override fun check(): Boolean {
                    return checked
                }
            }
    }
}
//end::init[]
