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
    // kodein
    val kodeinVersion = rootProject.extra.get("dep.kodein_version") as String
    implementation("org.kodein.di:kodein-di-framework-android-x:$kodeinVersion")

    // retrofit
    val retrofitVersion = rootProject.extra.get("dep.retrofit.retrofit_version") as String
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion")

    // rx
    val rxAndroidVersion = rootProject.extra.get("dep.rx.rx_android_version") as String
    val rxKotlinVersion = rootProject.extra.get("dep.rx.rx_kotlin_version") as String
    implementation("io.reactivex.rxjava2:rxandroid:$rxAndroidVersion")
    implementation("io.reactivex.rxjava2:rxkotlin:$rxKotlinVersion")
    implementation("com.jakewharton.rxrelay2:rxrelay:$rxAndroidVersion")

    // gson
    val gsonVersion = rootProject.extra.get("dep.gson_version") as String
    implementation("com.google.code.gson:gson:$gsonVersion")

    implementation(project(":model"))
    implementation(project(":services"))

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}