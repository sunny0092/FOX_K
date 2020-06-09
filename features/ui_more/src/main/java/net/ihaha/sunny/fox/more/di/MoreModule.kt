package net.ihaha.sunny.fox.more.di

import net.ihaha.sunny.fox.more.MoreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module


private val domainModule = module {

}

private val viewModelModule = module {
    viewModel { MoreViewModel() }
}

private val repositoriesModule = module {

}

private val presentationModule = module {

}

fun injectFeature() = loadFeature

fun removeFeature() = unLoadFeature

private val loadFeature by lazy { load() }

private val unLoadFeature by lazy { unLoad() }

private fun load() =
    loadKoinModules(listOf(domainModule, viewModelModule, repositoriesModule, presentationModule))

private fun unLoad() =
    loadKoinModules(listOf(domainModule, viewModelModule, repositoriesModule, presentationModule))
