import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class MyTest {

    @get:Rule
    internal val coroutineRule = MainCoroutineRule()

    @Test
    fun test() = coroutineRule.runBlockingTest {
        val viewModel = MyViewModel(coroutineRule.dispatcher)
        assertEquals("Val1", viewModel.x.first())
        advanceTimeBy(4000)

        assertEquals("Val2", viewModel.x.first())
    }
}

class MyViewModel(d: CoroutineDispatcher) {
    private val coroutineScope = CoroutineScope(SupervisorJob() + d)

    val x = MutableSharedFlow<String>()

    init {
        x.tryEmit("Val1")
        coroutineScope.launch {
            delay(3000)
            x.tryEmit("Val2")
        }
    }
}
