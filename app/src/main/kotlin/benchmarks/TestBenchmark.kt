package benchmarks


import io.reactivex.Observable
import io.reactivex.rxkotlin.concatAll
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors.toList
import kotlin.experimental.ExperimentalTypeInference
import kotlin.random.Random

val la = IntArray(1_000_000) { Random.nextInt() }.asList()
val lb = List(1_000) { Random.nextInt() }

@State(Scope.Benchmark)
@Fork(1)
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 10)
@Measurement(iterations = 10, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
class TestBenchmark {


    @Setup
    fun setUp() {
        runBlocking {}
    }

//
//    @Benchmark
//    fun a(blackhole: Blackhole) {
//        val r = Observable.just(lb)
//            .flatMapSingle { l ->
//                val new = l.map { foo(it) }
//                Observable.merge(new).toList()
//            }
//            .toList()
//            .blockingGet()
//        blackhole.consume(r)
//    }


    @Benchmark
    fun a(blackhole: Blackhole) {
        val r = Observable.just(lb)
            .flatMapSingle { l ->
                val new = l.map { foo(it) }
                Observable.merge(new).toList()
            }
            .toList()
            .blockingGet()
        blackhole.consume(r)
    }

    @Benchmark
    fun b(blackhole: Blackhole) {
        val r = Observable.just(lb)
            .flatMapSingle { l ->
                val new = l.map { foo(it) }
                Observable.concat(new).toList(new.size)
            }
            .toList()
            .blockingGet()
        blackhole.consume(r)
    }
//
//    @Benchmark
//    fun c(blackhole: Blackhole) {
//        runBlocking {
//
//            val r = flowOf(lb)
//                .map { l ->
//                    l.map { async { "id=$it" } }.awaitAll()
//                }.toList()
//            blackhole.consume(r)
//        }
//    }


//    @Benchmark
//    fun a(blackhole: Blackhole) {
//        val s = la.sum()
//        blackhole.consume(s)
//    }
//
//    @Benchmark
//    fun b(blackhole: Blackhole) {
//        val s = lb.sum()
//        blackhole.consume(s)
//    }

}

fun foo(id: Int) = Observable.just("id=$id")
