package qqq//import java.util.*
//import kotlin.math.min
//
//private fun fff() {
//    val l1 = listOf(listOf(listOf(1, 2), listOf(0, 3, 4)), listOf(listOf(1)))
//    println(l1.compareTo(l1))
//    println(l1.sortedWith { o1, o2 -> o1.compareTo(o2) })
//
//    l1.sortedWith { o1, o2 -> o1.compareTo(o2) }
//}
//
//
//@Suppress("UNCHECKED_CAST")
//fun <T : Any> compareOrThrow(a: T, b: T): Int = when (a) {
//    is Comparable<*> -> (a as Comparable<Any>).compareTo(b)
//    is Iterable<*> -> (a as Iterable<Any>).compareTo(b as Iterable<Any>)
//    is Array<*> -> (a as Array<Any>).compareTo(b as Array<Any>)
//    is IntArray -> Arrays.compare(a as IntArray, b as IntArray)
//    // other primitive arrays
//    else -> error("cannot compare elements")
//}
//
//operator fun <T : Any> Array<T>.compareTo(other: Array<T>): Int {
//    val minSize: Int = min(this.size, other.size)
//    for (i in 0 until minSize) {
//        val cmp = compareOrThrow(this[i], other[i])
//        if (cmp != 0) {
//            return cmp
//        }
//    }
//    return this.size - other.size
//}
//
//operator fun <T : Any> Iterable<T>.compareTo(other: Iterable<T>): Int {
//    val it1 = this.iterator()
//    val it2 = other.iterator()
//    while (it1.hasNext() && it2.hasNext()) {
//        val v1 = it1.next()
//        val v2 = it2.next()
//
//        val cmp = compareOrThrow(v1, v2)
//        if (cmp != 0) {
//            return cmp
//        }
//    }
//    return when {
//        !it1.hasNext() && !it2.hasNext() -> 0
//        it1.hasNext() -> 1
//        else -> -1
//    }
//}
//
//fun orderRuleFoo() {
//
//    val orderRule = listOf("foo", "bar", "asd")
//    val shuffled = listOf("foo", "bar", "asd", "abc", "efg", "foobar", "barfoo", "foo_abc", "bar foo").shuffled()
//
//    val sorted = shuffled.sortedWith(Comparator { a, b ->
//        val cmp = a.compareTo(b)
//        if (cmp == 0) return@Comparator 0
//        val ind1 = orderRule.indexOf(a)
//        val ind2 = orderRule.indexOf(b)
//        when {
//            ind1 == -1 && ind2 == -1 -> cmp
//            ind1 == -1 -> 1
//            ind2 == -1 -> -1
//            else -> ind1 - ind2
//        }
//    })
//    println(sorted)
//}
