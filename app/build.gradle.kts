plugins {
    id(AndroidConfig.Plugins.androidApplication)
    id(AndroidConfig.Plugins.kotlinAndroid)
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
    }

    buildTypes {
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
    implementation(Dependencies.kotlin)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)
    // Test
    testImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.testEtxJunit)
    androidTestImplementation(Dependencies.espressoCore)
}