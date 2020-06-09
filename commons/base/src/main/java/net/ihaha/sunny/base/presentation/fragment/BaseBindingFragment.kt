package net.ihaha.sunny.base.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import net.ihaha.sunny.base.viewModels.IBaseViewModel
import net.ihaha.sunny.base.BR

abstract class BaseBindingFragment<T : ViewDataBinding, out V : IBaseViewModel> : BaseFragment<V>() {

    protected lateinit var viewBinding: T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (::viewBinding.isInitialized.not()) {
            viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            viewBinding.apply {
                setVariable(BR.viewModel, viewModel)
                root.isClickable = true
                lifecycleOwner = viewLifecycleOwner
                executePendingBindings()
            }
        }
        return viewBinding.root
    }

    override fun onDestroyView() {
        viewBinding.unbind()
        super.onDestroyView()
    }
}