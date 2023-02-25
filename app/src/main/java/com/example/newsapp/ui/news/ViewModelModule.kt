package com.example.newsapp.ui.news

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(FragmentComponent::class)
object ViewModelModule {

    @Provides
    fun provideViewModelStore():ViewModelStore{
        return ViewModelStore()
    }
    @Provides
    fun provideViewModelOwner(viewModelStore: ViewModelStore):ViewModelStoreOwner{
        return ViewModelStoreOwner({ viewModelStore })
    }

    @Provides
    fun provideViewModel(viewModelStoreOwner: ViewModelStoreOwner,factory:ViewModelProvider):ViewModel{
        return ViewModelProvider(viewModelStoreOwner).get(NewsViewModel::class.java)
    }
}