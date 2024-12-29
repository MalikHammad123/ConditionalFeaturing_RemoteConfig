plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)


    alias(libs.plugins.dagger.hilt)        // Dagger-Hilt for dependency injection
    alias(libs.plugins.kotlin.kapt)

    id ("com.google.gms.google-services") // Add this line
}

android {
    namespace = "com.example.conditionalflagging_remoteconfig"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.conditionalflagging_remoteconfig"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField ("boolean", "FIREBASE_REMOTE_CONFIG_DEBUG_MODE", "false")

        }
        debug {
            buildConfigField ("boolean", "FIREBASE_REMOTE_CONFIG_DEBUG_MODE", "true")

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}


dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(platform("com.google.firebase:firebase-bom:32.1.0"))
    implementation("com.google.firebase:firebase-config-ktx")

    implementation(libs.hilt.android)                    // Hilt Android dependency
    kapt(libs.hilt.compiler)

    // Coroutines
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.2")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.2")
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.5.2")


    // Include this for StateFlow support in Compose
    implementation("androidx.compose.runtime:runtime:1.5.2") // For StateFlow support in Compose

    // Ensure flow is explicitly included
    //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-flow:1.8.2") // Add this line

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
