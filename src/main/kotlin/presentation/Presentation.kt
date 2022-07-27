package presentation

import kotlinx.coroutines.delay
import presentation.java_utils.PlatformTypes
import java.lang.reflect.Method
































/* ====================================================== */
//                The Kotlin Type System                  //
/* ====================================================== */

// a lot of the awesome features Kotlin gives us comes from its type system


















// TYPE VS CLASS

val str: String = "string" // <--- String is both the class and the type
val nullableStr: String? = "string" // <--- String is the class but String? is the type

// with collections, one class could represent a bunch of types (probably infinite),
// every one of the collections defined below is an instance of the class List but
// their types are different!

val numberCollection: List<Int> = listOf()
val stringCollection: List<String> = listOf()
val weirdNestedCollection: List<List<Pair<List<Int>, Boolean>>> = listOf()



















// and this is how we get the null safety we all love!

val number: Number = Int.MAX_VALUE // Number is a super type of Int
val something: Any = number // Any is a super type of everything
val string: String = "string" // String is both class and type
val nullable: String? = "string"
val nonNullable: String = nullable // Will not compile, String? is not a subtype of String




















// THE NOTHING TYPE

// why does this method compile?
fun toDoLater(): List<Boolean> {
    TODO()
}

fun unboxingNullables(nullableParameter: String?) {
    val nonNullable: String = nullableParameter ?: throw Throwable()
    nonNullable.uppercase()
}

fun willNeverComplete() {
    // this assignment will never complete!
    val something: Nothing = TODO()
}

















// Any corresponds to java.lang.Object, for example (watch the bytecode!):
fun any() {
    val value: Any = Any()
    (value as Object).wait()
}




















// UNIT VS VOID

// Unit is a singleton
val u = Unit

fun default(): Unit { }

// why do we need it?
interface ResultOrError<out T: Any, out E: Throwable>
class Example: ResultOrError<Unit, Throwable>




















// PLATFORM TYPES

// we know of these:
val nullable2: String? = ""
val nonNullable2: String = ""

// but, there is another:
val couldBeAnything: String!

// THIS IS A NOTATION AND NOT SYNTAX!

fun platformTypesConfusion() {
    val javaList = PlatformTypes.getJavaList()
    // ???
    javaList?.add(2)
    javaList.add(3)

    val nullList = PlatformTypes.getNullList()
    // ?!?
    nullList?.add(5)
    nullList.add(4)

    val kotlinList: MutableList<Int>? = mutableListOf()
    kotlinList?.add(3)
    kotlinList.add(9)
}














// so what should we do?
// annotate your Java code, and if you can't do that
// at least explicitly type values coming from Java

fun errorWithoutExplicitType() {
    val list = PlatformTypes.getNullList()
    list.iterator() // will crash with an NPE
}

fun errorWithExplicitType() {
    val list: List<Int> = PlatformTypes.getNullList()
    list.iterator() // will crash with an ILLEGAL STATE EXCEPTION
}




















/* ====================================================== */
//                    Inline Methods                      //
/* ====================================================== */






















fun inlineExample() {
    val list = listOf(1, 2, 3)
    val mapped = list.map { it + 1 }
    println(mapped)
}

fun afterCompilation() {
    val list = listOf(1, 2, 3)
    val mapped = mutableListOf<Int>()
    for (item in list) {
        mapped.add(item + 1)
    }
    println(mapped)
}















// so inlining injects the code into the calling method
// why? Lambda overhead

inline fun sayHello() {
    println("Hello!")
}
// OK, so this is used for lambdas, is there anything to keep in mind?
// YES!
// Don't use it with big lambdas





















// and to add even more confusion:
inline fun <T, E> veryConfusionAtFirst(
    crossinline transformation: (T) -> E,
    noinline anotherTransformation: (E) -> T
) { }























// noinline:
inline fun doOnDebug(
    log: (String) -> Unit,
    block: () -> Unit
) {
    block()
    flush(log)
}

fun flush(log: (String) -> Unit) {
    // flush the logger to a file
}

// that means that inlined arguments can only be invoked!
// why is that?




















// non-local returns:

fun foo(block: () -> Unit) {
    block()
}

inline fun bar(block: () -> Unit) {
    block()
}

fun baz() {
    val x = foo {
        println("trapped in bar!")
        return
        return@foo // <- to exit this lambda
    }
    bar {
        println("exiting bar!")
        return
    }
}

















// passing lambdas around in an inline method is forbidden
// the lambda could contain a non-local return
inline fun f(body: () -> Unit) {
    val f = object: Runnable {
        override fun run() = body()
    }
    // ...
}

// a quick fix, crossinline!
inline fun f2(crossinline body: () -> Unit) {
    val f = object: Runnable {
        override fun run() = body()
    }
    // ...
}

fun foo1() {
    f {
        println("try to exit...")
        return
    }

    f2 {
        println("try again...")
        return // <- not allowed in a crossinline method!
    }
}


















// suspend magic

suspend fun getStringSlowly(): String {
    delay(1000)
    return "slow result"
}

fun printFiveTimes(generator: () -> String) {
    repeat(5) {
        println(generator())
    }
}

suspend fun caller() {
    printFiveTimes {
        getStringSlowly()
    }
}

suspend fun whatThe() {
    repeat(5) {
        println(getStringSlowly())
    }
}























// reified types
fun <T> methodsOf(clazz: Class<T>): Array<Method> = clazz.methods

fun callSite() {
    val ugly = methodsOf(String::class.java)
    val betterLooking = methodsOf<String>()
}

// type is erased at runtime...
fun <T> methodsOf(): Array<Method> = T::class.java.methods

inline fun <reified T> methodsOfType(): Array<Method> = T::class.java.methods

// NOTE: An inlined function with reified type is not callable from Java code.























/* ====================================================== */
//                     Infix Methods                      //
/* ====================================================== */

























fun infixExample() {
    val pair: Pair<Int, String> = 3 to "String"
    val anotherPair: Pair<Int, Boolean> = 3.to(true)
}
























// must be a member function or an extension function
infix fun bad(arg: Int) { }

class Foo {
    infix fun good(arg: Int) { }
}

infix fun Foo.alsoGood(arg: Int) { }

// must have a single parameter
infix fun Foo.bad(arg: Int, another: Int) { }

// The parameter must not accept variable number of arguments and must have no default value
infix fun Foo.bad2(vararg args: Int) { }
infix fun Foo.bad3(arg: Int = 0) { }

// we can use generics
infix fun <T> Foo.generify(t: T) { }

fun example() {
    val foo = Foo()
    foo generify "string"
}




















/* ====================================================== */
//                  Extension Methods                     //
/* ====================================================== */
























class Dog

fun Dog.bark() { println("woof!") }

fun extExample() {
    val dog = Dog()
    dog.bark()
}


























// IMPLICIT RECEIVERS FOR LAMBDAS

fun execute(block: (String) -> Unit) {
    block("Hello!")
}

fun executeInContext(block: String.() -> Unit) {
    // this:
    block("Hello")
    // is the same as this:
    "Hello".block()
}












