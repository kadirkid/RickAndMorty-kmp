/**
 * Copyright 2023 Abdulahi Osoble
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "RickAndMortyApp"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(libs.koin.core)
                implementation(libs.bundles.paging)
                implementation(libs.kotlinx.coroutines.core)
                implementation(projects.core)
                implementation(projects.design)
                implementation(projects.service)
                implementation(projects.serviceApi)
                implementation(projects.util)
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
                implementation(libs.androidx.navigation.compose)
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
    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDir("src/commonRes")
        }
    }

    defaultConfig {
        applicationId = "dev.kadirkid.rickandmorty.androidApp"
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()

    packaging.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"

    buildTypes {
        getByName("release").isMinifyEnabled = false
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