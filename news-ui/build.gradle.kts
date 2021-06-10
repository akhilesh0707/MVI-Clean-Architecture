plugins {
    id(AndroidConfig.Plugins.androidApplication)
    id(AndroidConfig.Plugins.kotlinAndroid)
    id(AndroidConfig.Plugins.kotlinKapt)
    id(AndroidConfig.Plugins.dagger)
    id(AndroidConfig.Plugins.navigationSafArgs)
}

android {
    compileSdkVersion(AndroidConfig.androidCompileSdkVersion)
    buildToolsVersion(AndroidConfig.androidBuildToolsVersion)

    defaultConfig {
        applicationId(AndroidConfig.appId)
        minSdkVersion(AndroidConfig.androidMinSdkVersion)
        targetSdkVersion(AndroidConfig.androidTargetSdkVersion)
        versionCode(AndroidConfig.appVersionCode)
        versionName(AndroidConfig.appVersionName)

        testInstrumentationRunner(AndroidConfig.testRunner)

        // Configs
        buildConfigField("String", "BASE_URL", "\"" + AndroidConfig.Debug.baseUrl + "\"")
        buildConfigField("String", "API_KEY", "\"086340faa4d049fda83047d1eda69733\"")
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        viewBinding = true
    }
}

dependencies {
    // Modules
    implementation(project(AndroidConfig.Modules.presentation))
    implementation(project(AndroidConfig.Modules.domain))
    implementation(project(AndroidConfig.Modules.data))

    // Core dependencies
    implementation(Dependencies.kotlin)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)

    implementation(Dependencies.navigationFragmentKtx)
    implementation(Dependencies.navigationUiKtx)

    // LifeCycle
    implementation(Dependencies.viewModelKtx)
    implementation(Dependencies.liveDataKtx)
    implementation(Dependencies.lifeCycleExtension)
    implementation(Dependencies.lifeCycleRuntime)
    implementation(Dependencies.lifeCycleRuntimeKtx)

    // Coroutines
    implementation(Dependencies.coroutineCore)
    implementation(Dependencies.coroutineAndroid)

    // Timber logging
    implementation(Dependencies.timber)

    // Glide
    implementation(Dependencies.glide)
    kapt(Dependencies.glideKapt)

    // Hilt dependencies
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidKapt)
    kapt(Dependencies.hiltKapt)

    // Paging
    implementation(Dependencies.paging)

    // Test
    testImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.testEtxJunit)
    androidTestImplementation(Dependencies.espressoCore)
}