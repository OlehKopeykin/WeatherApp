// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    extra.apply {
        set("kotlin_version", "1.5.21")

        set("gradle_tools_version", "7.0.2")

        set("dep.androidx.appcompat_version", "1.3.1")
        set("dep.androidx.material_version", "1.4.0")
        set("dep.androidx.recyclerview_version", "1.1.0")
        set("dep.androidx.constraint_version", "2.1.0")
        set("dep.androidx.lifecycle_version", "2.4.0-beta01")
        set("dep.androidx.navigation_version", "2.3.5")
        set("dep.androidx.compose_version", "1.0.2")
        set("dep.androidx.activity_compose_version", "1.3.1")
        set("dep.androidx.data_binding_compiler_version", "3.2.0")
        set("dep.androidx.room_version", "2.3.0")

        set("dep.retrofit.retrofit_version","2.6.2")
        set("dep.retrofit.logging_interceptor_version","4.2.2")

        set("dep.kodein_version","6.1.0")

        set("dep.multidex_version","2.0.1")

        set("dep.gson_version","2.8.6")

        set("dep.rx.rx_android_version","2.1.1")
        set("dep.rx.rx_kotlin_version","2.2.0")
        set("dep.rx.rx_relay_version","2.1.1")
        set("dep.rx.binding_version","2.0.0")

        set("dep.glide_version","4.12.0")

        set("dep.lottie_version","3.3.1")
    }

    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        val gradleToolsVersion = rootProject.extra.get("gradle_tools_version") as String
        val kotlinVersion = rootProject.extra.get("kotlin_version") as String

        classpath("com.android.tools.build:gradle:${gradleToolsVersion}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}