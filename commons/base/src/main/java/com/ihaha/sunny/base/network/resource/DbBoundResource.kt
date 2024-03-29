package com.ihaha.sunny.base.network.resource

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import com.ihaha.sunny.base.network.remote.DataState
import com.ihaha.sunny.base.network.remote.DataType
import com.ihaha.sunny.base.network.remote.MessageType
import com.ihaha.sunny.base.network.remote.pairOf

/**
 * Represents a resource which needs to be fetched from the database only
 *
 * @param T The type of the entity to be fetched/stored in the database
 *
 * The items emitted from this class are wrapped in a [DataState] class. The items are emitted in a [Flow].
 * Following is sequence of actions taken:
 * 1. Emit Resource.Loading
 * 2. Query database for cached data using [fetchFromDb], and emit [DataState.SUCCESS] if it passes [validateData]
 * else emit [DataState.ERROR]
 *
 * The class also contains two properties [offset] and [limit] so that they can be dynamically updated. They are
 * passed to [getFromDatabase] on every invocation. They can be updated using the [updateParams] method.
 */
abstract class DbBoundResource<T : Any> {

    private var dbLimit: Int = -1
    private var dbOffset: Int = 0

    abstract suspend fun fetchFromDb(): T?
    abstract suspend fun validateData(data: T?): Boolean

    @ExperimentalCoroutinesApi
    fun flow(): Flow<DataState<T?>> = flow {
        val cached = fetchFromDb()
        if (validateData(cached)) {
            emit(DataState.SUCCESS(data = cached, dataType = DataType.DATABASE))
        } else {
            emit(DataState.ERROR(data = null, error = null))
        }
    }.onStart { emit(DataState.LOADING) }

    fun updateParams(limit: Int = dbLimit, offset: Int = dbOffset) {
        dbLimit = limit
        dbOffset = offset
    }
}

@ExperimentalCoroutinesApi
inline fun <T : Any> DbBoundResource(
    crossinline initialParams: () -> Pair<Int, Int> = { pairOf(-1, 0) },
    crossinline dbFetcher: suspend () -> T?,
    crossinline validator: suspend (T?) -> Boolean
): Flow<DataState<T?>> {
    val resource = object : DbBoundResource<T>() {
        init {
            val (limit, offset) = initialParams()
            updateParams(limit, offset)
        }
        override suspend fun fetchFromDb(): T? {
            return dbFetcher()
        }

        override suspend fun validateData(data: T?): Boolean {
            return validator(data)
        }
    }
    return resource.flow()
}