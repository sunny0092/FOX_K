package com.ihaha.sunny.fox.more

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import com.ihaha.sunny.base.presentation.fragment.BaseBindingFragment
import com.ihaha.sunny.fox.more.databinding.FragmentMoreBinding
import com.ihaha.sunny.fox.more.di.injectFeature
import com.ihaha.sunny.fox.more.di.removeFeature
import com.ihaha.sunny.fox.ui.callback.OnListenerChangeLanguage
import com.ihaha.sunny.fox.ui.callback.OnListenerChangeTheme
import com.ihaha.sunny.ui.extensions.string
import com.ihaha.sunny.utils.prefs.SharePrefsManager
import com.ihaha.sunny.utils.prefs.SharedPrefKeys
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.android.inject

/**
 * Create by Sunny date: 25-04-2020
 * Version: 1.0.0
 */

@FlowPreview
@ExperimentalCoroutinesApi
class MoreFragment : BaseBindingFragment<FragmentMoreBinding, MoreViewModel>() {

    //region variable

    private val sharePrefsManager : SharePrefsManager by inject()

    private var onListenerChangeTheme: OnListenerChangeTheme? = null
    private var onListenerChangeLanguage: OnListenerChangeLanguage? = null

    //endregion

    //region override

    override val viewModel: MoreViewModel by activityViewModels()

    override val layoutId: Int = R.layout.fragment_more

    //endregion

    //region override

    companion object{
        fun newInstances() = MoreFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
//        setThemeListener()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when(context){
            is OnListenerChangeTheme -> onListenerChangeTheme = context
            is OnListenerChangeLanguage -> onListenerChangeLanguage = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeature()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        initEventListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        removeFeature()
    }

    //endregion

    //region method
    private fun initComponents() {

    }

    private fun initEventListener() {
        setThemeListener()
    }

    private fun setThemeListener(){
        if(sharePrefsManager.getTheme() == SharedPrefKeys.Theme.LIGHT_MODE){
            viewBinding.tvThemeName.text = string(R.string.title_theme_dark)
            viewBinding.swcTheme.isChecked = false
        }else{
            viewBinding.tvThemeName.text = string(R.string.title_theme_light)
            viewBinding.swcTheme.isChecked = true
        }

        if(sharePrefsManager.getLanguage() == SharedPrefKeys.Language.VIETNAMESE){
            viewBinding.tvLanguageName.text = string(R.string.title_language_vietnamese)
            viewBinding.swcLanguage.isChecked = false
        }else{
            viewBinding.tvLanguageName.text = string(R.string.title_language_english)
            viewBinding.swcTheme.isChecked = true
        }

        viewBinding.swcTheme.setOnCheckedChangeListener { _, isChecked ->
            onListenerChangeTheme?.changeTheme(isChecked)
            if(isChecked)
                viewBinding.tvThemeName.text = string(R.string.title_theme_light)
            else
                viewBinding.tvThemeName.text = string(R.string.title_theme_dark)
//            refresh()
        }
        viewBinding.swcLanguage.setOnCheckedChangeListener { _, isChecked ->
            onListenerChangeLanguage?.changeLanguage(isChecked)
            if(isChecked)
                viewBinding.tvLanguageName.text = string(R.string.title_language_vietnamese)
            else
                viewBinding.tvLanguageName.text = string(R.string.title_language_english)
        }
    }

    //endregion
}