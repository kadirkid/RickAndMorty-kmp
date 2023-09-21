import com.android.build.gradle.internal.ide.kmp.KotlinAndroidSourceSetMarker.Companion.android

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrain.compose)
}

compose {
    kotlinCompilerPlugin = dependencies.compiler.forKotlin("1.9.0")
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=1.9.10")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()
    explicitApi()

    jvm("desktop")

    js(IR) {
        moduleName = "app"
        browser()
        binaries.executable()
    }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(projects.core)
                implementation(projects.design)
                implementation(projects.serviceApi)
                implementation(libs.koin.core)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.palette.multiplatform)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.coil.compose)
                implementation(compose.uiTooling)
                implementation(libs.androidx.core.ktx)
                implementation(libs.androidx.lifecycle.process)
                implementation(libs.androidx.lifecycle.runtime)
                implementation(libs.androidx.activity.compose)
                implementation(libs.androidx.activity)
                implementation(libs.androidx.appcompat)
                implementation(libs.koin.android)
                implementation(libs.koin.androidx.compose)
                implementation(libs.kotlinx.coroutines.android)
//                implementation(libs.androidx.navigation.common.ktx)
            }
        }

        val desktopMain by getting {
            resources.srcDir("src/commonRes")
            dependencies {
                implementation(compose.ui)
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.coroutines.swing)
            }
        }

        val jsMain by getting {
            resources.srcDirs("src/commonRes", "../rickandmorty/src/commonRes")
            dependencies {
                implementation(projects.design)
                implementation(libs.kotlinx.coroutines.core.js)
                implementation(compose.ui)
            }
        }
    }
}

android {
    namespace = "dev.kadirkid.rickandmorty.app"
    compileSdk = 34
    defaultConfig.minSdk = 21
    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDir("src/commonRes")
        }
    }

    defaultConfig {
        applicationId = "dev.kadirkid.rickandmorty.androidApp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = "1.5.2"

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

compose {
    desktop.application {
        mainClass = "dev.kadirkid.rickandmorty.app.DesktopAppKt"
        nativeDistributions {
            packageName = "DesktopApp"
            macOS.bundleID = "dev.kadirkid.rickandmorty.app"
        }
    }

    experimental.web.application {}
}