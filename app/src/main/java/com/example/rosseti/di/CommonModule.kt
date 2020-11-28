package com.example.rosseti.di

import com.example.rosseti.domain.entities.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class CommonModule {

    @Provides
    @Singleton
    fun provideUser(): User = User()
}