//package com.ihaha.sunny.base.presentation.holders
//
//import android.view.View
//import android.view.ViewGroup
//import com.mikepenz.fastadapter.FastAdapter
//import kotlinx.android.extensions.LayoutContainer
//import com.ihaha.sunny.ui.extensions.clear
//
//abstract class BaseViewHolder<I : BaseRecyclerUI<*>>(
//    override val containerView: View
//) : FastAdapter.ViewHolder<I>(containerView), LayoutContainer {
//    override fun unbindView(item: I) {
//        (containerView as? ViewGroup)?.clear()
//    }
//}