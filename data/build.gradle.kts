plugins {
    id(AndroidConfig.Plugins.kotlin)
    id(AndroidConfig.Plugins.javaLibrary)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    // Modules
    implementation(project(AndroidConfig.Modules.domain))

}