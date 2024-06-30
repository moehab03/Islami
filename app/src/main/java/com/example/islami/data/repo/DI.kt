package com.example.islami.data.repo

import com.example.islami.data.api.WebServices
import com.example.islami.domian.repo.RadioRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DI {
    @Provides
    fun provideAuthRepo(webServices: WebServices): RadioRepository =
        RadioRepoImpl(webServices)
}
