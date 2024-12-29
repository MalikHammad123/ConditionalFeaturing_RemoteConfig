package com.example.conditionalflagging_remoteconfig.viewmodel

import androidx.lifecycle.ViewModel

import com.example.conditionalflagging_remoteconfig.RemoteConfigManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FeatureViewModel @Inject constructor(
    private val remoteConfigManager: RemoteConfigManager
) : ViewModel() {

    private val _featuresState = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val featuresState: StateFlow<Map<String, Boolean>> get() = _featuresState.asStateFlow()

    init {
        fetchFeatures()
    }

    private fun fetchFeatures() {
        remoteConfigManager.fetchAndActivate { isSuccess ->
            if (isSuccess) {
                val features = mapOf(
                    "feature_1" to remoteConfigManager.isFeatureEnabled("feature_1"),
                    "feature_2" to remoteConfigManager.isFeatureEnabled("feature_2"),
                    "feature_3" to remoteConfigManager.isFeatureEnabled("feature_3")
                )
                // Update StateFlow with only enabled features
                _featuresState.value = features.filter { it.value }
            }
        }
    }
}