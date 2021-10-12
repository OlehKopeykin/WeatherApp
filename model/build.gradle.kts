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
    // gson
    val gsonVersion = rootProject.extra.get("dep.gson_version") as String
    implementation("com.google.code.gson:gson:$gsonVersion")

    // dagger
    val daggerVersion = rootProject.extra.get("dep.dagger_version") as String
    implementation("com.google.dagger:dagger:${daggerVersion}")
    kapt("com.google.dagger:dagger-compiler:${daggerVersion}")

    //room
    val androidxRoomVersion = rootProject.extra.get("dep.androidx.room_version") as String
    implementation("androidx.room:room-runtime:$androidxRoomVersion")
    implementation("androidx.room:room-rxjava2:$androidxRoomVersion")
    kapt("androidx.room:room-compiler:$androidxRoomVersion")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}