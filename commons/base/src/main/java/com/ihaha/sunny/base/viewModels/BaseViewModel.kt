package com.ihaha.sunny.base.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rasalexman.coroutinesmanager.ICoroutinesManager
import com.rasalexman.coroutinesmanager.SuspendCatch
import com.rasalexman.coroutinesmanager.SuspendTry
import com.rasalexman.coroutinesmanager.launchOnUITryCatch
import com.ihaha.sunny.ui.data.dto.SEvent
import com.ihaha.sunny.ui.data.dto.SResult
import com.ihaha.sunny.ui.extensions.errorResult

//open class BaseViewModel : ViewModel(), IBaseViewModel{}

open class BaseViewModel : ViewModel(), IBaseViewModel {

    override val eventLiveData = MutableLiveData<SEvent>()

    override val anyLiveData: LiveData<*>? = null
    override val resultLiveData: LiveData<*>? = null
    override val errorLiveData = MutableLiveData<SResult<*>>()

    protected open val defaultCatchBlock: SuspendCatch<Unit> = {
        errorLiveData.value = errorResult(message = it.message.orEmpty(), exception = it)
    }

    /**
     * Base Function to add to ViewStateHandler Some Values by key [String]
     */
    override fun <T : Any> addToSaveState(key: String, value: T) = Unit

    /**
     * Base Function fro getting value from ViewStateHandler by key [String]
     */
    override fun <T : Any> getFromSaveState(key: String): T? = null

    /**
     *
     */
    fun ICoroutinesManager.launchOnUIDefault(tryBlock: SuspendTry<Unit>) =
        launchOnUITryCatch(tryBlock, defaultCatchBlock)

}
