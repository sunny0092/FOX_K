import dependencies.Dependencies
import extensions.*
import extensions.implementation
import org.gradle.kotlin.dsl.dependencies

plugins {
    id("commons.android-library")
    id(BuildPlugins.KOTLIN_KAPT)
}

dependencies {
    //MODULES
    implementation(project(BuildModules.COMMONS.BASE))
    implementation(project(BuildModules.COMMONS.UI))
    implementation(project(BuildModules.CORES.CACHE))
    implementation(project(BuildModules.CORES.LOCAL))
    implementation(project(BuildModules.CORES.REMOTE))
    implementation(project(BuildModules.CORES.DOMAIN))
    //FIREBASE
    implementation(Dependencies.FIREBASE_AUTH)
    implementation(Dependencies.FIREBASE_CORE)
    implementation(Dependencies.FIREBASE_DATABASE)
    implementation(Dependencies.FIREBASE_ANALYTICS)
    implementation(Dependencies.FIREBASE_FIRE_STORE)
    implementation(Dependencies.FIREBASE_FIRE_STORAGE)
    implementation(Dependencies.FIREBASE_CRASHLYTICS)
    implementation(Dependencies.FIREBASE_MESSAGING)
    implementation(Dependencies.GOOGLE_AUTH_LOGIN)
    implementation(Dependencies.GOOGLE_AUTH_PHONE)
    implementation(Dependencies.CRASHLYTICS)

    implementation(Dependencies.NETWORK_RESPONSE)
    //DATA
    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KTX)
    kapt(Dependencies.ROOM_COMPILER)

    //FIREBASE

}