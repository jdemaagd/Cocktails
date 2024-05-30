import com.android.build.gradle.internal.plugins.AppPlugin
import com.android.build.gradle.internal.plugins.LibraryPlugin
import com.diffplug.gradle.spotless.SpotlessExtension

buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.spotless) apply false
}

subprojects {

    apply {
        plugin("com.diffplug.spotless")
    }

    plugins.matching { anyPlugin -> anyPlugin is AppPlugin || anyPlugin is LibraryPlugin }.whenPluginAdded {
        apply(plugin = libs.plugins.spotless.get().pluginId)
        extensions.configure<SpotlessExtension> {
            kotlin {
                target("**/*.kt")
                targetExclude("$layout.buildDirectory/**/*.kt")
                ktlint()
                    .setEditorConfigPath("${project.rootDir}/spotless/.editorconfig")
            }
        }
    }
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
