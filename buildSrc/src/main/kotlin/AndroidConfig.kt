object AndroidConfig {
    // Android sdk and version
    const val androidMinSdkVersion = 21
    const val androidTargetSdkVersion = 30
    const val androidCompileSdkVersion = 30
    const val androidBuildToolsVersion = "30.0.3"

    const val appId = "com.aqube.mvi"
    const val appVersionCode = 1
    const val appVersionName = "1.0"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"

    object Plugins {
        const val androidApplication = "com.android.application"
        const val kotlinAndroid = "kotlin-android"
        const val kotlinKapt = "kotlin-kapt"
        const val dagger = "dagger.hilt.android.plugin"
    }
}