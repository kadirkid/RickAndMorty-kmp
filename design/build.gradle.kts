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
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrain.compose)
}

compose {
    val kotlinVersion = libs.versions.kotlin.get()
    kotlinCompilerPlugin.set(dependencies.compiler.forKotlin(kotlinVersion))
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=$kotlinVersion")
}

kotlin {
    applyDefaultHierarchyTemplate()
    explicitApi()

    jvm("desktop")

    js(IR) { browser() }

    androidTarget()

    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.ui)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
        }

        androidMain.dependencies {
            implementation(libs.coil.compose)
            implementation(compose.uiTooling)
        }

        val desktopMain by getting {
            resources.srcDir("src/commonRes")
        }

        jsMain.dependencies {
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.js)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "dev.kadirkid.rickandmorty.design"
    sourceSets {
        named("main") {
            res.srcDir("src/commonRes")
        }
    }
}