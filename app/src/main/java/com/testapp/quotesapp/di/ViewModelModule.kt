package com.testapp.quotesapp.di

import android.content.SharedPreferences
import com.testapp.quotesapp.common.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class) // These component will be available until ViewModel exits. in our case (HostViewModel)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideViewModelScopeRandomQuote(sharedPreferences:SharedPreferences): Pair<String?, String?> {
        val randomAuthorName = sharedPreferences.getString(AppConstant.AUTHOR_NAME, "")
        val randomAuthorQuote = sharedPreferences.getString(AppConstant.AUTHOR_QUOTE, "")
        return Pair(randomAuthorName, randomAuthorQuote)
    }
    
}