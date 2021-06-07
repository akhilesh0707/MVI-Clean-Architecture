object ClassPaths {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val daggerHiltGradle =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltAndroidVersion}"
    const val navigationSafArgsGradle =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.gradleNavigationArgVersion}"
}