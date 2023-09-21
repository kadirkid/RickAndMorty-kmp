import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class SpotlessConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.diffplug.spotless")
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            extensions.configure<SpotlessExtension> {
                kotlin {
                    target("**/*.kt", "**/*.kts")
                    targetExclude("$buildDir/**/*.kt", "**/copyright.kt", "**/build/**")
                    ktlint(libs.findVersion("ktlint").get().toString())
                    licenseHeaderFile(rootProject.file("$rootDir/spotless/copyright.kt"))
                    trimTrailingWhitespace()
                    endWithNewline()
                }
                format("kts") {
                    target("**/*.kts")
                    targetExclude("$buildDir/**/*.kts", "**/copyright.kts", "**/build/**")
                    // Look for the first line that doesn't have a block comment (assumed to be the license)
                    licenseHeaderFile(rootProject.file("spotless/copyright.kts"), "(^(?![\\/ ]\\*).*$)")
                }
                format("xml") {
                    target("**/*.xml")
                    targetExclude("**/build/**/*.xml", "**/copyright.xml", "**/build/**")
                    // Look for the first XML tag that isn't a comment (<!--) or the xml declaration (<?xml)
                    licenseHeaderFile(rootProject.file("spotless/copyright.xml"), "(<[^!?])")
                }
            }
        }
    }
}