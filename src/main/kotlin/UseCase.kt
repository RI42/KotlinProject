
//import kotlinx.coroutines.delay
//
//typealias Foo = suspend () -> Int
//
//interface UseCase<Param, R> {
//    operator fun invoke(param: Param): R
//    operator fun invoke(builder: Param.() -> Unit): R
//}
//
//class UseCaseImpl : UseCase<UseCaseImpl.UseCaseImplParam, RResult<Int>> {
//
//    val f = suspend {
//        delay(100)
//        1
//
//    }
//
//    override operator fun invoke(param: UseCaseImplParam): RResult<Int> {
//        return RResult.success(param.age)
//    }
//
//    override operator fun invoke(builder: UseCaseImplParam.() -> Unit): RResult<Int> {
//        return foo(UseCaseImplParam().apply(builder))
//    }
//
//    data class UseCaseImplParam(var name: String = "", var age: Int = 0)
//
//}
//
//fun foo(p: UseCaseImpl.UseCaseImplParam): RResult<Int> {
//    return RResult.success(p.age)
//}
