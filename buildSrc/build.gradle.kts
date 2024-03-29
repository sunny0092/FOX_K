plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    jcenter()
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://plugins.gradle.org/m2/")
    maven("https://ci.android.com/builds/submitted/5837096/androidx_snapshot/latest/repository")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

object PluginsVersions {
    const val GRADLE_ANDROID = "4.1.0-beta02"
    const val GRADLE_VERSIONS = "0.28.0"
    const val KOTLIN = "1.3.72"
    const val NAVIGATION = "2.3.0"
    const val JACOCO = "0.17.0-SNAPSHOT"
    const val FABRIC = "1.31.2"
    const val DOKKA = "0.10.0"
    const val KTLINT = "0.37.2"
    const val SPOTLESS = "4.4.0"
    const val DETEKT = "1.10.0-RC1"
    const val GRAPH_GENERATOR = "0.6.0-SNAPSHOT"
    const val GOOGLE_SERVICES = "4.3.3"
    const val CRASHLYTICS = "2.1.1"
    const val PERFORMANCE = "1.3.1"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    implementation("com.google.firebase:firebase-crashlytics-gradle:${PluginsVersions.CRASHLYTICS}")
    implementation("com.google.firebase:perf-plugin:${PluginsVersions.PERFORMANCE}")
    implementation("com.google.gms:google-services:${PluginsVersions.GOOGLE_SERVICES}")
    implementation("com.github.ben-manes:gradle-versions-plugin:${PluginsVersions.GRADLE_VERSIONS}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")
    implementation("org.jetbrains.kotlin:kotlin-allopen:${PluginsVersions.KOTLIN}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION}")
    implementation("com.vanniktech:gradle-android-junit-jacoco-plugin:${PluginsVersions.JACOCO}")
    implementation("com.vanniktech:gradle-dependency-graph-generator-plugin:${PluginsVersions.GRAPH_GENERATOR}")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:${PluginsVersions.DOKKA}")
    implementation("com.pinterest:ktlint:${PluginsVersions.KTLINT}")
//    implementation("com.diffplug.spotless:spotless-plugin-gradle:${PluginsVersions.SPOTLESS}")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${PluginsVersions.DETEKT}")
}



