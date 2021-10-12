plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = 31
    defaultConfig {
        minSdk = 23
        targetSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    // dagger
    val daggerVersion = rootProject.extra.get("dep.dagger_version") as String
    implementation("com.google.dagger:dagger:${daggerVersion}")
    kapt("com.google.dagger:dagger-compiler:${daggerVersion}")

    // retrofit
    val retrofitVersion = rootProject.extra.get("dep.retrofit.retrofit_version") as String
    val retrofitLoggingInterceptorVersion =
        rootProject.extra.get("dep.retrofit.logging_interceptor_version") as String
    api("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$retrofitLoggingInterceptorVersion")

    // rx
    val rxAndroidVersion = rootProject.extra.get("dep.rx.rx_android_version") as String
    val rxKotlinVersion = rootProject.extra.get("dep.rx.rx_kotlin_version") as String
    implementation("io.reactivex.rxjava2:rxandroid:$rxAndroidVersion")
    implementation("io.reactivex.rxjava2:rxkotlin:$rxKotlinVersion")
    implementation("com.jakewharton.rxrelay2:rxrelay:$rxAndroidVersion")

    //room
    val androidxRoomVersion = rootProject.extra.get("dep.androidx.room_version") as String
    implementation("androidx.room:room-runtime:$androidxRoomVersion")
    implementation("androidx.room:room-rxjava2:$androidxRoomVersion")
    kapt("androidx.room:room-compiler:$androidxRoomVersion")

    implementation(project(":model"))

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}