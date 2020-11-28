package com.example.rosseti.di

import com.example.rosseti.application.Form
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class FormModule {

    @Provides
    @Singleton
    fun provideForm(): Form = Form()
}