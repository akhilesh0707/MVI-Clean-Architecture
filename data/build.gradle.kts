plugins {
    id(AndroidConfig.Plugins.androidLibrary)
    id(AndroidConfig.Plugins.kotlinAndroid)
    id(AndroidConfig.Plugins.kotlinKapt)
}

android {
    compileSdkVersion(AndroidConfig.androidCompileSdkVersion)
    defaultConfig {
        minSdkVersion(AndroidConfig.androidMinSdkVersion)
        targetSdkVersion(AndroidConfig.androidTargetSdkVersion)
        versionCode(AndroidConfig.appVersionCode)
        versionName(AndroidConfig.appVersionName)

        testInstrumentationRunner(AndroidConfig.testRunner)
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
dependencies {
    // Modules
    implementation(project(AndroidConfig.Modules.domain))

    // Kotlin coroutines
    implementation(Dependencies.kotlin)
    implementation(Dependencies.coroutineCore)
    // JavaX
    implementation(Dependencies.Data.javax)
    // Network (Retrofit, moshi, interceptor)
    implementation(Dependencies.Data.retrofit)
    implementation(Dependencies.Data.moshiConverter)
    implementation(Dependencies.Data.loggingInterceptor)

    // Room
    implementation(Dependencies.Data.roomKtx)
    api(Dependencies.Data.roomRuntime)
    kapt(Dependencies.Data.roomCompilerKapt)

    // Timber logging
    implementation(Dependencies.Data.timber)
}