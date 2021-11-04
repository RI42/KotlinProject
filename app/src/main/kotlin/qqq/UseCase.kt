package qqq
//import kotlinx.coroutines.delay
//
//typealias Foo = suspend () -> Int
//
//interface UseCase<Param, R> {
//    operator fun invoke(param: Param): R
//    operator fun invoke(builder: Param.() -> Unit): R
//}
//
//class UseCaseImpl : UseCase<UseCaseImpl.UseCaseImplParam, qqq.RResult<Int>> {
//
//    val f = suspend {
//        delay(100)
//        1
//
//    }
//
//    override operator fun invoke(param: UseCaseImplParam): qqq.RResult<Int> {
//        return qqq.RResult.success(param.age)
//    }
//
//    override operator fun invoke(builder: UseCaseImplParam.() -> Unit): qqq.RResult<Int> {
//        return foo(UseCaseImplParam().apply(builder))
//    }
//
//    data class UseCaseImplParam(var name: String = "", var age: Int = 0)
//
//}
//
//fun foo(p: UseCaseImpl.UseCaseImplParam): qqq.RResult<Int> {
//    return qqq.RResult.success(p.age)
//}
