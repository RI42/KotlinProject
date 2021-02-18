package benchmarks


import kotlinx.coroutines.*
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors.toList

@State(Scope.Benchmark)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 10, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
class TestBenchmark {

    val l = (0..63).toList()

    @Setup
    fun setUp() {
        Dispatchers.IO
    }


    @Benchmark
    fun a(blackhole: Blackhole) {

        blackhole.consume(runBlocking {
            l.pmap {
                getDataAsync("$it")
            }
        })
    }

    @Benchmark
    fun b(blackhole: Blackhole) {
        blackhole.consume(
            l.parallelStream()
                .map { getDataAsync("$it") }
                .collect(toList())
        )
    }

}

fun getDataAsync(url: String): String {
    Thread.sleep(100)
    return "str:$url"
}

suspend fun <A, B> Iterable<A>.pmap(f: suspend (A) -> B): List<B> =
    coroutineScope {
        map { async(Dispatchers.IO) { f(it) } }.awaitAll()
    }