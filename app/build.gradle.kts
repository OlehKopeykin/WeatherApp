plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = 31
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "by.olegkopeykin.weather"
        multiDexEnabled = true
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        // Enables Jetpack Compose for this module
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dataBinding {
        isEnabled = true
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    composeOptions {
        kotlinCompilerExtensionVersion =
            rootProject.extra.get("dep.androidx.compose_version") as String
    }
}

dependencies {
    // multidex
    val multidexVersion = rootProject.extra.get("dep.multidex_version") as String
    implementation("androidx.multidex:multidex:$multidexVersion")

    // appcompat
    val androidxAppCompatVersion = rootProject.extra.get("dep.androidx.appcompat_version") as String
    implementation("androidx.appcompat:appcompat:$androidxAppCompatVersion")

    // constraint
    val androidxConstraintVersion =
        rootProject.extra.get("dep.androidx.constraint_version") as String
    implementation("androidx.constraintlayout:constraintlayout:$androidxConstraintVersion")

    // databinding
    val androidxDataBindingCompilerVersion =
        rootProject.extra.get("dep.androidx.data_binding_compiler_version") as String
    kapt("com.android.databinding:compiler:$androidxDataBindingCompilerVersion")

    // material
    val androidxMaterialVersion = rootProject.extra.get("dep.androidx.material_version") as String
    implementation("com.google.android.material:material:$androidxMaterialVersion")

    // recycler view
    val androidxRecyclerViewVersion =
        rootProject.extra.get("dep.androidx.recyclerview_version") as String
    implementation("androidx.recyclerview:recyclerview-selection:$androidxRecyclerViewVersion")

    // lifecycle
    val androidxLifecycleVersion = rootProject.extra.get("dep.androidx.lifecycle_version") as String
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$androidxLifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-common-java8:$androidxLifecycleVersion")

    // compose
    val androidxComposeVersion = rootProject.extra.get("dep.androidx.compose_version") as String
    val androidxActivityComposeVersion =
        rootProject.extra.get("dep.androidx.activity_compose_version") as String
    implementation("androidx.activity:activity-compose:$androidxActivityComposeVersion")
    implementation("androidx.compose.material:material:$androidxComposeVersion")
    implementation("androidx.compose.animation:animation:$androidxComposeVersion")
    implementation("androidx.compose.ui:ui-tooling:$androidxComposeVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$androidxLifecycleVersion")

    // navigation
    val androidxNavigationVersion =
        rootProject.extra.get("dep.androidx.navigation_version") as String
    implementation("androidx.navigation:navigation-fragment-ktx:$androidxNavigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$androidxNavigationVersion")

    // kodein
    val kodeinVersion = rootProject.extra.get("dep.kodein_version") as String
    implementation("org.kodein.di:kodein-di-generic-jvm:$kodeinVersion")
    implementation("org.kodein.di:kodein-di-framework-android-core:$kodeinVersion")
    implementation("org.kodein.di:kodein-di-framework-android-x:$kodeinVersion")

    // rx
    val rxAndroidVersion = rootProject.extra.get("dep.rx.rx_android_version") as String
    val rxKotlinVersion = rootProject.extra.get("dep.rx.rx_kotlin_version") as String
    val rxBindingVersion = rootProject.extra.get("dep.rx.binding_version") as String
    implementation("io.reactivex.rxjava2:rxandroid:$rxAndroidVersion")
    implementation("io.reactivex.rxjava2:rxkotlin:$rxKotlinVersion")
    implementation("com.jakewharton.rxrelay2:rxrelay:$rxAndroidVersion")
    implementation("com.jakewharton.rxbinding2:rxbinding-design-kotlin:$rxBindingVersion")

    // lottie
    val lottieVersion = rootProject.extra.get("dep.lottie_version") as String
    implementation("com.airbnb.android:lottie:$lottieVersion")

    // glide
    val glideVersion = rootProject.extra.get("dep.glide_version") as String
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    implementation("com.github.bumptech.glide:okhttp3-integration:$glideVersion")
    kapt("com.github.bumptech.glide:compiler:$glideVersion")

    implementation(project(":interactors"))
    implementation(project(":model"))
    implementation(project(":services"))

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}