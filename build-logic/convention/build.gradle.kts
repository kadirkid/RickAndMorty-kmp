import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    `kotlin-dsl`
}

group = "dev.kadirkid.rickandmorty.buildlogic"

java {
    val javaVersion = libs.versions.jvmTarget.map(JavaVersion::toVersion).get()
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions.jvmTarget.set(libs.versions.jvmTarget.map(JvmTarget::fromTarget).get())
}

dependencies {
//    compileOnly(libs.gradle.android)
    compileOnly(libs.gradle.kotlin)
    compileOnly(libs.gradle.spotless)
}
gradlePlugin {
    plugins {
        register("spotless") {
            id = "rickandmorty.spotless"
            implementationClass = "SpotlessConventionPlugin"
        }
    }
}