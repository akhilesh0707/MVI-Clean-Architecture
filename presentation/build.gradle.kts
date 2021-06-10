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
    implementation(project(AndroidConfig.Modules.domain))

    implementation(Dependencies.kotlin)
    implementation(Dependencies.coroutineCore)

    // JavaX
    implementation(Dependencies.javax)

    // LifeCycle
    implementation(Dependencies.viewModelKtx)
    implementation(Dependencies.liveDataKtx)
    implementation(Dependencies.lifeCycleExtension)
    implementation(Dependencies.lifeCycleRuntime)
    implementation(Dependencies.lifeCycleRuntimeKtx)

    // Hilt dependencies
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidKapt)
    kapt(Dependencies.hiltKapt)

    // Paging
    implementation(Dependencies.paging)
}