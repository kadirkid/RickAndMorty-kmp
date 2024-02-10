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
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()
    explicitApi()

    jvm("desktop")

    js(IR) { browser() }

    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets.commonMain {
        dependencies {
            implementation(libs.koin.core)
            implementation(libs.apollo.runtime)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.bundles.paging)
            implementation(libs.arrow.core)
            implementation(projects.service)
            implementation(projects.serviceApi)
            implementation(projects.design)
            implementation(projects.util)
        }
    }
}

android {
    namespace = "dev.kadirkid.rickandmorty.core"
    sourceSets {
        named("main") {
            res.srcDir("src/commonRes")
        }
    }
}