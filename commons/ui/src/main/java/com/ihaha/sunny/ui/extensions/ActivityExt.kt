package com.ihaha.sunny.ui.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog
import com.ihaha.sunny.ui.R

fun Activity.hideKeyboard() {
    val v = currentFocus ?: View(this)
    if (v is EditText) v.clearFocus()
    v.windowToken?.let {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(
            it,
            0
        )
    }
}

fun Activity.showKeyboard(view: View) {
    val v = currentFocus ?: view
    if (v is EditText) v.requestFocus()
    (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.showSoftInput(
        v,
        SHOW_IMPLICIT
    )
}

fun Activity.displayToast(msg: String?) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.displayToast(@StringRes msg: Int) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
