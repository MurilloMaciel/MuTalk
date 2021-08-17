object Datastore {
    object Versions {
        const val version = "1.0.0-rc01"
    }

    const val datastore = "androidx.datastore:datastore:${Versions.version}"
    const val core = "androidx.datastore:datastore-core:${Versions.version}"
}

object Compose {
    object Versions {
        const val version = "1.0.0"
    }

    const val ui = "androidx.compose.ui:ui:${Versions.version}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.version}"
    const val foundation = "androidx.compose.foundation:foundation:${Versions.version}"
    const val material = "androidx.compose.material:material:${Versions.version}"
    const val animation = "androidx.compose.animation:animation:${Versions.version}"
    const val runtime = "androidx.compose.runtime:runtime:${Versions.version}"
    const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:${Versions.version}"
    const val uiTest = "androidx.compose.ui:ui-test-junit4:${Versions.version}"
}

object Firebase {
    object Versions {
        const val analytics = "19.0.0"
        const val platform = "26.8.0"
        const val auth = "21.0.1"
        const val storage = "20.0.0"
        const val firestore = "23.0.3"
    }

    const val platform = "com.google.firebase:firebase-bom:${Versions.platform}"
    const val analytics = "com.google.firebase:firebase-analytics:${Versions.analytics}"
    const val auth = "com.google.firebase:firebase-auth-ktx:${Versions.auth}"
    const val storage = "com.google.firebase:firebase-storage:${Versions.storage}"
    const val firestore = "com.google.firebase:firebase-firestore-ktx:${Versions.firestore}"
}

object Layout {
    object Versions {
        const val constraintLayout = "2.1.0"
    }

    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}

object Kotlin {
    object Versions {
        const val kotlin = "1.5.21"
    }

    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
}

object JUnit {
    object Versions {
        const val junit = "4.13.2"
    }

    const val core = "junit:junit:${Versions.junit}"
}

object AndroidXTest {
    object Versions {
        const val junit = "1.1.1"
        const val espresso = "3.2.0"
        const val archCore = "2.1.0"
    }

    const val ext = "androidx.test.ext:junit:${Versions.junit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val archCore = "androidx.arch.core:core-testing:${Versions.archCore}"
}

object MockK {
    object Versions {
        const val mockk = "1.12.0"
        const val android = "1.9.3"
    }

    const val core = "io.mockk:mockk:${Versions.mockk}"
    const val android = "io.mockk:mockk-android:${Versions.android}"
}

object Wire {
    object Versions {
        const val version = "3.7.0"
    }

    const val runtime = "com.squareup.wire:wire-runtime:${Versions.version}"
}

object WorkManager {
    object Versions {
        const val workManager = "2.4.0"
    }

    const val runtime = "androidx.work:work-runtime-ktx:${Versions.workManager}"
    const val test = "androidx.work:work-testing:${Versions.workManager}"
}

object Lifecycle {
    object Versions {
        const val lifecycle = "2.3.1"
        const val lifecycleTesting = "2.1.0"
        const val extensions = "2.2.0"
    }

    const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
}

object OkHttp {
    object Versions {
        const val okhttp = "4.9.1"
    }

    const val core = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
}

object Room {
    object Versions {
        const val room = "2.2.6"
    }

    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.room}"
    const val test = "androidx.room:room-testing:${Versions.room}"
}

object Moshi {
    object Versions {
        const val moshi = "1.12.0"
    }

    const val core = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val codeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
}

object Coroutines {
    object Versions {
        const val coroutines = "1.5.0"
    }

    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val playServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines}"
}

object Retrofit {
    object Versions {
        const val retrofit = "2.9.0"
    }

    const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
}

object Glide {
    object Versions {
        const val glide = "4.12.0"
    }

    const val core = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object AndroidX {
    object Versions {
        const val ktx = "1.6.0"
        const val appcompat = "1.3.1"
        const val design = "1.4.0"
        const val activity = "1.3.0"
    }

    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val design = "com.google.android.material:material:${Versions.design}"
    const val activity = "androidx.activity:activity-ktx:${Versions.activity}"
}

object Hilt {
    object Versions {
        const val hilt = "2.35"
    }

    const val core = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
}