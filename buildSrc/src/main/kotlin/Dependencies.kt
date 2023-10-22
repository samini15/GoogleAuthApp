import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val composeLifecycle = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.composeLifecycle}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.composeMaterial}"
    const val composeUI = "androidx.compose.ui:ui"
    const val composeUIGraphics = "androidx.compose.ui:ui-graphics"
    const val composeUIToolingPreview = "androidx.compose.ui:ui-tooling-preview"

    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val daggerHiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.daggerHiltNavigationCompose}"
    const val daggerHiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerHiltAndroidCompiler}"
    const val daggerHiltCompiler = "androidx.hilt:hilt-compiler:${Versions.daggerHiltCompiler}"
    const val daggerHiltAgb = "com.google.dagger:hilt-android-gradle-plugin:2.44"

    const val dataStorePreferences = "androidx.datastore:datastore-preferences:${Versions.dataStorePreferences}"

    const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerialization}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitKtxSerializationConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitKtxSerializationConverter}"
    const val okHttpUrlConnection = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.okHttpUrlConnection}"

    const val googlePlayServicesAuth = "com.google.android.gms:play-services-auth:${Versions.playServicesAuth}"

    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
}

fun DependencyHandler.compose() {
    implementation(Dependencies.composeActivity)
    implementation(Dependencies.composeLifecycle)
    implementation(Dependencies.composeNavigation)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeUI)
    implementation(Dependencies.composeUIGraphics)
    implementation(Dependencies.composeUIToolingPreview)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitKtxSerializationConverter)
    implementation(Dependencies.okHttpUrlConnection)
}

fun DependencyHandler.daggerHilt() {
    implementation(Dependencies.daggerHilt)
    implementation(Dependencies.daggerHiltNavigationCompose)
    kapt(Dependencies.daggerHiltAndroidCompiler)
    kapt(Dependencies.daggerHiltCompiler)
}

fun DependencyHandler.dataStorePreferences() {
    implementation(Dependencies.dataStorePreferences)
}

fun DependencyHandler.kotlinSerialization() {
    implementation(Dependencies.kotlinSerialization)
}

fun DependencyHandler.googleAuth() {
    implementation(Dependencies.googlePlayServicesAuth)
}

fun DependencyHandler.coil() {
    implementation(Dependencies.coil)
}