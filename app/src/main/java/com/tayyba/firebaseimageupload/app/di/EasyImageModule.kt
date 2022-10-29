package com.tayyba.firebaseimageupload.app.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.aprilapps.easyphotopicker.EasyImage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EasyImageModule {

    @Provides
    @Singleton
    fun provideEasyImage(application: Application) = EasyImage.Builder(application)
        .setCopyImagesToPublicGalleryFolder(false)
        .allowMultiple(true)
        .build()
}