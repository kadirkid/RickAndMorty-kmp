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
                implementation(libs.koin.core)
                implementation(libs.apollo.runtime)
                implementation(libs.kotlinx.coroutines.core)
                implementation(projects.service)
                implementation(projects.serviceApi)
                implementation(projects.design)
                implementation(projects.util)
            }
        }
    }
}

android {
    namespace = "dev.kadirkid.rickandmorty.core"
    compileSdk = 34
    defaultConfig.minSdk = 21
    sourceSets {
        named("main") {
            res.srcDir("src/commonRes")
        }
    }
}