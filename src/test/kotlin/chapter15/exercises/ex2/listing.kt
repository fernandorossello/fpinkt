package chapter15.exercises.ex2

 import chapter10.None
 import chapter10.Option
 import chapter10.Some
 import chapter15.sec2.Await
 import chapter15.sec2.Emit
 import chapter15.sec2.Halt
import chapter15.sec2.Process
import chapter15.sec2.toList
import chapter3.List
import chapter5.Stream
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

//tag::init[]
fun <I> count(): Process<I, Int> {
    fun go(acc: Int): Process<I,Int> =
        Await { i: Option<I> ->
            when (i) {
                is None -> Halt<I,Int>()
                is Some -> Emit(acc+1,go(acc+1))
            }
        }
   return go(0)
}

//end::init[]

class Exercise2 : WordSpec({
    "count" should {
        "emit a stream of numbers representing elements consumed" {
            val stream = Stream.of("a", "b", "c", "d")
            val p = count<String>()
            p(stream).toList() shouldBe List.of(1, 2, 3, 4)
        }
    }
})
