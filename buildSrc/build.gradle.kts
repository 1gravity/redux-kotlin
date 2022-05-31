plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    api("com.android.tools.build:gradle:7.2.1")
}
