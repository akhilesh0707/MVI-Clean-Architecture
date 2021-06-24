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
        const val kotlin = "kotlin"
        const val kotlinAndroid = "kotlin-android"
        const val kotlinKapt = "kotlin-kapt"
        const val javaLibrary = "java-library"
        const val dagger = "dagger.hilt.android.plugin"
        const val navigationSafArgs = "androidx.navigation.safeargs.kotlin"
        const val androidLibrary = "com.android.library"
    }

    object Modules {
        const val domain = ":domain"
        const val data = ":data"
        const val presentation = ":presentation"
    }

    object Debug {
        const val baseUrl = "https://newsapi.org/v2/"
        const val apiKey = "086340faa4d049fda83047d1eda69733"
    }
}