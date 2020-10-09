package benchmarks


import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.awt.SystemColor.text
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@State(Scope.Benchmark)
@Fork(1)
//@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
open class TestBenchmark {
    val dictionary: HashSet<String> = hashSetOf("cat", "fox", "dog", "fish", "jumps")


//    @Setup
//    fun setup() {
//
//    }

    @Benchmark
    fun a(blackhole: Blackhole) {
        val text = StringBuilder("The quick brown fox jumps over the lazy dog.")
        val m = "\\b\\w{3,}\\b".toPattern().matcher(text)
        while (m.find()) {
            if (m.group() in dictionary) {
                val s = m.start()
                val e = m.end()
                text.replace(
                    s + 1, e - 1,
                    "*".repeat(e - s - 2)
                )
            }
        }
        blackhole.consume(text)
    }


    @Benchmark
    fun b(blackhole: Blackhole) {
        val text = StringBuilder("The quick brown fox jumps over the lazy dog.")
        val m = "\\b\\w{3,}\\b".toPattern().matcher(text)
        while (m.find()) {
            if (m.group() in dictionary) {
                text.replace(
                    m.start() + 1, m.end() - 1,
                    "*".repeat(10)
                )
            }
        }
        blackhole.consume(text)
    }


//
//    @Benchmark
//    fun a(blackhole: Blackhole) {
//        val text = StringBuilder("The quick brown fox jumps over the lazy dog.")
//
//        blackhole.consume(text)
//    }
//
//
//    @Benchmark
//    fun b(blackhole: Blackhole) {
//        val text = StringBuilder("The quick brown fox jumps over the lazy dog.")
//
//        blackhole.consume(text)
//    }

}
