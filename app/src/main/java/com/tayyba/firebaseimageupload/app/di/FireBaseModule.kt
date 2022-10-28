package com.tayyba.firebaseimageupload.app.di

import com.google.firebase.firestore.FirebaseFirestore
import com.tayyba.firebaseimageupload.app.firebase_utils.FireBaseUserAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FireBaseModule{

    @Singleton
    @Provides
    fun mFireBaseFireStore():FireBaseUserAuth {
        return FireBaseUserAuth(FirebaseFirestore.getInstance())
    }

}