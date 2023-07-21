buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(libs.sql.delight.gradle.plugin)
        classpath(libs.moko.resources.plugin)
        classpath("com.arkivanov.parcelize.darwin:gradle-plugin:0.1.4")
    }
}

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.compose").apply(false)
}
