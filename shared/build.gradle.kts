plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version "1.5.0"
    id("com.android.library")
    id("org.jetbrains.compose")
    id("com.squareup.sqldelight")
    id("kotlin-parcelize")
    id("com.arkivanov.parcelize.darwin")
}

kotlin {
    android()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                // koin
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                // Ktor
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negolation)
                implementation(libs.ktor.json)
                implementation(libs.coroutines.core)
                // Serialization
                implementation(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.datetime)
                // Logging
                implementation(libs.napier)
                // DB
                implementation(libs.sql.delight.runtime)
                implementation(libs.sql.delight.coroutines.extensions)
                // Moko
                implementation(libs.moko.viewmodel)
                implementation(libs.moko.flow)
                implementation(libs.moko.permissions)
                implementation(libs.moko.media)
                // Decompose
                implementation(libs.decompose)
                implementation(libs.decompose.compose.multiplatform)

                // Navigator
                implementation(libs.voyager.core)
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.transitions)

            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.activity.compose)
                api(libs.core.ktx)

                implementation(libs.ktor.client.okhttp)
                implementation(libs.sql.delight.android)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.ktor.client.darwin)
                implementation(libs.sql.delight.ios)
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.myapplication.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "db"
    }
}
