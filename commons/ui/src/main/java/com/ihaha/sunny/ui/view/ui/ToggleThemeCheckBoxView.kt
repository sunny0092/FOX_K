/*
 * Copyright 2020 Jose Maria Payá Castillo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ihaha.sunny.ui.view.ui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import com.ihaha.sunny.ui.R

/**
 * Animated button menu item check box to apply night/light mode.
 *
 * @see AppCompatCheckBox
 */
class ToggleThemeCheckBoxView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : AppCompatCheckBox(context, attrs) {
    init {
        setButtonDrawable(R.drawable.selector_theme)
    }
}
