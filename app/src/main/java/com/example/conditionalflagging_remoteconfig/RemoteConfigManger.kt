package com.example.conditionalflagging_remoteconfig

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.conditionalflagging_remoteconfig.viewmodel.FeatureViewModel
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import javax.inject.Inject

class RemoteConfigManager @Inject constructor() {
    private val remoteConfig = FirebaseRemoteConfig.getInstance()

    init {
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(1) // Fetch interval
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(
            mapOf(
                "feature_1" to true,
                "feature_2" to true,
                "feature_3" to true
            )
        )
    }

    fun fetchAndActivate(onComplete: (Boolean) -> Unit) {
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            onComplete(task.isSuccessful)
        }
    }

    fun isFeatureEnabled(featureKey: String): Boolean {
        return remoteConfig.getBoolean(featureKey)
    }
}
