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
                implementation(libs.kotlinx.datetime)
                implementation(libs.apollo.runtime)
//                implementation(libs.apollo.httpcache)
                implementation(libs.apollo.api)
                implementation(libs.apollo.normalizedCache)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.koin.core)
                implementation(projects.serviceApi)
                implementation(projects.util)
            }
        }
    }
}

android {
    namespace = "dev.kadirkid.rickandmorty.service"
    compileSdk = 34
    defaultConfig.minSdk = 21
    sourceSets {
        named("main") {
            res.srcDir("src/commonRes")
        }
    }
}