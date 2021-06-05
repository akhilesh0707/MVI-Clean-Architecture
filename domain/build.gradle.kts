plugins {
    id(AndroidConfig.Plugins.kotlin)
    id(AndroidConfig.Plugins.javaLibrary)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    implementation(Dependencies.kotlin)
    implementation(Dependencies.coroutineCore)

    // JavaX
    implementation(Dependencies.javax)

}