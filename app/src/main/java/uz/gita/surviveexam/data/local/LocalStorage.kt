package uz.gita.surviveexam.data.local

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalStorage @Inject constructor(
    private val preferences: SharedPreferences
) {
    private val ONBOARDING_STATE = "ONBOARDING_STATE"

    var isFirstRun:Boolean
        get() = preferences.getBoolean(ONBOARDING_STATE,true)
        set(value) = preferences.edit().putBoolean(ONBOARDING_STATE,value).apply()

}