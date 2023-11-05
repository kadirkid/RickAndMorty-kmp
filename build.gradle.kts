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
import com.android.build.api.dsl.AndroidSourceSet
import com.android.build.api.dsl.ApplicationDefaultConfig
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryDefaultConfig
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.BaseExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import kotlin.reflect.KProperty

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.serializable) apply false
    alias(libs.plugins.jetbrain.compose) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.apollo) apply false
    alias(libs.plugins.poko) apply false
    alias(libs.plugins.spotless) apply false
    id("rickandmorty.spotless") apply true
}

subprojects {
    afterEvaluate {
        apply(plugin = "rickandmorty.spotless")
        pluginManager.withAnyKotlinPlugin {
            dependencies {
                "implementation"(enforcedPlatform(libs.arrow.bom))
            }
        }
    }
    kotlinSetup()
    androidSetup()
    jvmTargetConventionSetup()
}

fun Project.jvmTargetConventionSetup() {
    if ("tooling" in path) return // Don't set for tooling modules

    fun <T : Any> jvmTarget(map: (String) -> T) = libs.versions.jvmTarget.map(map).get()

    pluginManager.withPlugin("java") {
        configure<JavaPluginExtension> {
            val target = jvmTarget(JavaVersion::toVersion)
            sourceCompatibility = target
            targetCompatibility = target
        }
    }

    pluginManager.withAnyKotlinPlugin {
        tasks.withType<KotlinJvmCompile>().configureEach {
            compilerOptions.jvmTarget.set(jvmTarget(JvmTarget::fromTarget))
        }
    }

    pluginManager.withAnyAndroidPlugin {
        android.compileOptions {
            val target = jvmTarget(JavaVersion::toVersion)
            sourceCompatibility = target
            targetCompatibility = target
        }
    }
}

fun Project.androidSetup() {
    pluginManager.withAnyPlugin("android-library") {
        configure<BaseExtension> {

            defaultConfig {
                consumerProguardFile("src/androidMain/proguard-rules.pro")
            }
            disableBuildConfig()
        }
    }


    pluginManager.withAnyAndroidPlugin {
        android {
            compileSdk = libs.versions.compileSdk.get().toInt()
            defaultConfig {
                minSdk = libs.versions.minSdk.get().toInt()
                targetSdk = libs.versions.targetSdk.get().toInt()
            }
            sourceSets.addKotlinDirectories()
        }
    }
}

fun Project.kotlinSetup() {
    tasks.withType<KotlinCompile<*>>().configureEach {
        if (this is KotlinJvmCompile)
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs.toMutableList().apply {
                    add("-Xjvm-default=all")
                }
            }
    }
}

private typealias CommonExtensionT = CommonExtension<*, *, *, *, *>

private typealias ComponentsExtensionT = AndroidComponentsExtension<CommonExtensionT, *, *>

inline fun PluginManager.withAnyAndroidPlugin(action: Action<AppliedAndroidPlugin>) {
    withAnyPlugin("com.android.application", "com.android.library") {
        action.execute(AppliedAndroidPlugin(this))
    }
}

class AppliedAndroidPlugin(appliedPlugin: AppliedPlugin) : AppliedPlugin by appliedPlugin {

    inline val Project.android
        get() = extensions.getByName<CommonExtensionT>("android")

    inline fun Project.android(action: Action<CommonExtensionT>) =
        extensions.configure("android", action)

    inline val Project.androidComponents
        get() = extensions.getByName<ComponentsExtensionT>("androidComponents")

    inline fun Project.androidComponents(action: Action<ComponentsExtensionT>) =
        extensions.configure("androidComponents", action)
}

class AppliedKotlinPlugin(appliedPlugin: AppliedPlugin) : AppliedPlugin by appliedPlugin {
    inline val Project.kotlin
        get() = extensions.getByName<KotlinProjectExtension>("kotlin")

    inline fun Project.kotlin(action: Action<KotlinProjectExtension>) =
        extensions.configure("kotlin", action)
}

inline fun PluginManager.withAnyKotlinPlugin(action: Action<AppliedKotlinPlugin>) {
    withAnyPlugin(
        "org.jetbrains.kotlin.jvm",
        "org.jetbrains.kotlin.android",
        "org.jetbrains.kotlin.multiplatform"
    ) {
        action.execute(AppliedKotlinPlugin(this))
    }
}

inline fun PluginManager.withAnyPlugin(vararg plugins: String, action: Action<AppliedPlugin>) =
    plugins.forEach { withPlugin(it, action) }

inline fun NamedDomainObjectContainer<out AndroidSourceSet>.addKotlinDirectories() =
    configureEach {
        java.srcDir("src/$name/kotlin")
    }

inline fun <reified T : BaseExtension> T.disableBuildConfig() {
    buildFeatures.buildConfig = false
}

operator fun <T> SetProperty<T>.getValue(receiver: Any?, property: KProperty<*>): Set<T> = get()

operator fun <T> SetProperty<T>.setValue(
    receiver: Any?,
    property: KProperty<*>,
    value: Iterable<T>?
) = set(value)


var CommonExtensionT.targetSdk: Int?
    get() = when (this) {
        is LibraryDefaultConfig -> null
        is ApplicationDefaultConfig -> targetSdk
        else -> throw IllegalStateException("Not in an android receiver")
    }
    set(value) {
        if (this is ApplicationDefaultConfig) targetSdk = value
    }