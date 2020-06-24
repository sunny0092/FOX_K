package net.ihaha.sunny.base.presentation.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import net.ihaha.sunny.base.domain.DataState
import net.ihaha.sunny.base.domain.StateMessage
import net.ihaha.sunny.base.domain.UIComponentType
import net.ihaha.sunny.base.presentation.listeners.DataStateChangeListener
import net.ihaha.sunny.base.presentation.listeners.UICommunicationListener
import net.ihaha.sunny.base.utils.extentions.areYouSureDialog
import net.ihaha.sunny.base.utils.extentions.displayErrorDialog
import net.ihaha.sunny.base.utils.extentions.displayInfoDialog
import net.ihaha.sunny.ui.extensions.hideKeyboard
import net.ihaha.sunny.base.utils.lifecycle.LifeCycleObserverUtils
import net.ihaha.sunny.ui.extensions.displayToast
import org.koin.android.ext.android.inject


abstract class BaseActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId),
    DataStateChangeListener, UICommunicationListener {

    protected val autoLifeCycleObserver by lazy { LifeCycleObserverUtils(lifecycle) }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autoLifeCycleObserver.init(this)
    }

    override fun finish() {
        hideKeyboard()
        super.finish()
    }

    override fun onDataStateChangeListener(dataState: DataState<*>?) {
        dataState?.let {
            displayLoading(it.loading)
            it.stateMessage?.let { stateMessage ->
                handleResponseState(stateMessage)
            }
        }
    }

    override fun onUIMessageReceived(stateMessage: StateMessage) {
        when (stateMessage.uiComponentType) {
            is UIComponentType.AreYouSureDialog -> {
                areYouSureDialog(stateMessage.message, (stateMessage.uiComponentType).callBack)
            }
            is UIComponentType.DIALOG -> {
                displayInfoDialog(stateMessage.message)
            }
            is UIComponentType.TOAST -> {
                displayToast(stateMessage.message)
            }
            is UIComponentType.NONE -> {
            }
        }
    }

    private fun handleResponseState(stateMessage: StateMessage?) {
        stateMessage?.message?.let { message ->
            when (stateMessage.uiComponentType) {
                is UIComponentType.DIALOG -> {
                    displayErrorDialog(message)
                }
                is UIComponentType.TOAST -> {
                    displayToast(message)
                }
            }
        }
    }


}