package com.example.conditionalflagging_remoteconfig

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.conditionalflagging_remoteconfig.viewmodel.FeatureViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: FeatureViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {
        // Collect features state from the ViewModel
        val features by viewModel.featuresState.collectAsState()

        Column(verticalArrangement = Arrangement.Center, // Center items vertically
            horizontalAlignment = Alignment.CenterHorizontally, // Center items horizontally
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (features.isNotEmpty()) {
                features.forEach { (featureName, _) ->
                    Text(text = "$featureName is enabled!")
                }
            } else {
                Text(text = "No features are enabled.")
            }
        }
    }
}