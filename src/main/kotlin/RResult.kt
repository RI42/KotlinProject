class RResult<out T> private constructor(
    @PublishedApi internal val resource: Resource,
) {
    companion object {
        fun <T> loading(data: Any? = null): RResult<T> = RResult(Resource.Loading(data))
        fun <T> success(value: T): RResult<T> = RResult(Resource.Success(value))
        fun <T> failure(throwable: Throwable): RResult<T> = RResult(Resource.Failure(throwable))
    }

    val isLoading get() = resource is Resource.Loading
    val isSuccess get() = resource is Resource.Success
    val isFailure get() = resource is Resource.Failure

    @Suppress("UNCHECKED_CAST")
    fun successOrNull(): T? = (resource as? Resource.Success)?.value as? T

    @Suppress("UNCHECKED_CAST")
    fun successOrThrow(): T = when (resource) {
        is Resource.Loading -> throw IllegalStateException("Loading state")
        is Resource.Success -> resource.value as T
        is Resource.Failure -> throw resource.throwable
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <R, T> RResult<T>.map(transform: (value: T) -> R): RResult<R> {
    return when (resource) {
        is Resource.Loading -> RResult.loading(resource.data)
        is Resource.Success -> RResult.success(transform(resource.value as T))
        is Resource.Failure -> RResult.failure(resource.throwable)
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <R, T> RResult<T>.mapCatching(transform: (value: T) -> R): RResult<R> {
    return when (resource) {
        is Resource.Loading -> RResult.loading(resource.data)
        is Resource.Success ->
            try {
                RResult.success(transform(resource.value as T))
            } catch (t: Throwable) {
                RResult.failure(t)
            }
        is Resource.Failure -> RResult.failure(resource.throwable)
    }
}

inline fun <T> RResult<T>.onLoading(
    action: (data: Any?) -> Unit,
): RResult<T> {
    (resource as? Resource.Loading)?.let {
        action(it.data)
    }
    return this
}

@Suppress("UNCHECKED_CAST")
inline fun <T> RResult<T>.onSuccess(
    action: (value: T) -> Unit,
): RResult<T> {
    (resource as? Resource.Success)?.let {
        action(it.value as T)
    }
    return this
}

inline fun <T> RResult<T>.onFailure(
    action: (throwable: Throwable) -> Unit,
): RResult<T> {
    (resource as? Resource.Failure)?.let {
        action(it.throwable)
    }
    return this
}

@PublishedApi
internal sealed class Resource {
    data class Loading(val data: Any?) : Resource()
    data class Success(val value: Any?) : Resource()
    data class Failure(val throwable: Throwable) : Resource()
}
