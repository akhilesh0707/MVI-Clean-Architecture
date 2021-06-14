object Dependencies {
    // Dependencies
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val javax = "javax.inject:javax.inject:${Versions.javaxInjectVersion}"

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navFragmentKtxVersion}"
    const val navigationUiKtx =
        "androidx.navigation:navigation-ui-ktx:${Versions.navUiKtxVersion}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtxVersion}"

    // Coroutines
    const val coroutineCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutineCoreVersion}"
    const val coroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineAndroidVersion}"

    // Timber
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"

    // Hilt dependencies
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroidVersion}"
    const val hiltAndroidKapt = "com.google.dagger:hilt-compiler:${Versions.hiltAndroidVersion}"
    const val hiltKapt = "androidx.hilt:hilt-compiler:${Versions.hiltVersion}"

    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideKapt = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"

    // Test Dependencies
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val testEtxJunit = "androidx.test.ext:junit:${Versions.testEtxJunitVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"

    // LifeCycle
    const val viewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodelKtxVersion}"
    const val liveDataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveDataKtxVersion}"
    const val lifeCycleExtension =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycleExtVersion}"
    const val lifeCycleRuntime =
        "androidx.lifecycle:lifecycle-runtime:${Versions.lifeCycleRuntimeVersion}"
    const val lifeCycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleRuntimeKtxVersion}"

    // Paging
    const val paging = "androidx.paging:paging-runtime:${Versions.pagingVersion}"

    object Data {
        // Network dependencies
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        const val moshiConverter =
            "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverterVersion}"
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptorVersion}"

        // Timber
        const val timber = Dependencies.timber
        const val javax = Dependencies.javax

        const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
        const val roomCompilerKapt = "androidx.room:room-compiler:${Versions.roomVersion}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    }
}