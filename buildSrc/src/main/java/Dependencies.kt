object ApplicationId {
    const val id = "com.maciel.murillo.mutalk"
}

object Versions {
    const val compileSdk = 30
    const val buildToolsVersion = "30.0.3"
    const val minSdk = 21
    const val targetSdk = 30
    const val ndk = "21.3.6528147"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object GradlePlugin {
    object Versions {
        const val kotlin = "1.5.21"
        const val gradleVersionsPlugin = "0.38.0"
        const val googleServices = "4.3.10"
        const val androidGradlePlugin = "4.1.3"
        const val wire = "3.7.0"
        const val hilt = "2.35"
    }

    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val gradleVersions = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersionsPlugin}"
    const val googleServices = "com.google.gms:google-services:${Versions.googleServices}"
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val wire = "com.squareup.wire:wire-gradle-plugin:${Versions.wire}"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
}