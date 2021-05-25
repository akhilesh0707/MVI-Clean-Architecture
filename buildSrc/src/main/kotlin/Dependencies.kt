object Dependencies {
    // Dependencies
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"

    // Test Dependencies
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val testEtxJunit = "androidx.test.ext:junit:${Versions.testEtxJunitVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
}