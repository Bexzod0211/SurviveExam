package uz.gita.surviveexam.di

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    private val storage_name = "EXAM"

    @[Provides Singleton]
    fun provideSharedPreferences(@ApplicationContext context:Context):SharedPreferences =
        context.getSharedPreferences(storage_name,Context.MODE_PRIVATE)

    @Provides
    fun provideFirebaseAuth():FirebaseAuth = Firebase.auth

    @Provides
    fun provideFirebaseFireStore():FirebaseFirestore = Firebase.firestore
}