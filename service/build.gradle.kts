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
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.apollo)
}

apollo {
    service("service") {
        packageName.set("dev.kadirkid.rickandmorty")
        schemaFile.set(file("src/commonMain/graphql/schema.graphqls"))
        codegenModels.set("operationBased")
        generateDataBuilders.set(true)
        generateFragmentImplementations.set(true)
        generateSchema.set(true)
    }
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()
    explicitApi()

    jvm("desktop")

    js(IR) { browser() }

    androidTarget()

<<<<<<< Updated upstream
    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.datetime)
                implementation(libs.apollo.runtime)
                implementation(libs.apollo.api)
                implementation(libs.apollo.normalizedCache)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.koin.core)
                implementation(libs.arrow.core)
                implementation(libs.arrow.coroutines)
                implementation(projects.serviceApi)
                implementation(projects.util)
            }
        }
=======
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets.commonMain.dependencies {
        implementation(libs.kotlinx.datetime)
        implementation(libs.apollo.runtime)
        implementation(libs.apollo.api)
        implementation(libs.apollo.normalizedCache)
        implementation(libs.kotlinx.coroutines.core)
        implementation(libs.koin.core)
        implementation(libs.arrow.core)
        implementation(libs.arrow.coroutines)
        implementation(projects.serviceApi)
        implementation(projects.util)
>>>>>>> Stashed changes
    }
}

android {
    namespace = "dev.kadirkid.rickandmorty.service"
    sourceSets {
        named("main") {
            res.srcDir("src/commonRes")
        }
    }
}