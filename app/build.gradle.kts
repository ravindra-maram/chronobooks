//plugins {
//    alias(libs.plugins.android.application)
//}
//
//android {
//    namespace = "com.example.chronobooks"
//    compileSdk = 35
//
//    defaultConfig {
//        applicationId = "com.example.chronobooks"
//        minSdk = 30
//        targetSdk = 35
//        versionCode = 1
//        versionName = "1.0"
//
//    }
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
//    }
//}
//
//dependencies {
//
//    implementation(libs.play.services.wearable)
//    implementation(libs.appcompat)
//    implementation(libs.material)
//    implementation(libs.activity)
//    implementation(libs.constraintlayout)
//    implementation(libs.wear)
//    implementation(libs.retrofit)
//    implementation(libs.converter.gson)
//    implementation(libs.media3.exoplayer)
//    implementation(libs.media3.ui) // optional UI
//    implementation (libs.xandroidx.wear)
//    implementation (libs.tiles.material)
//    implementation (libs.compose.material)
//    implementation(libs.media3.session)
//}

plugins {
    alias(libs.plugins.android.application)
    id("org.jetbrains.kotlin.android") version "1.9.0"
    id("org.jetbrains.kotlin.kapt") version "1.9.0" // ✅ add versioned kapt
}

android {
    namespace = "com.example.chronobooks"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.chronobooks"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    // ✅ Add this block:
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true;
    }
}

dependencies {
    implementation(libs.play.services.wearable)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.wear)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.media3.exoplayer)
    implementation(libs.media3.ui)
    implementation(libs.media3.session)
    implementation(libs.tiles.material)
    implementation(libs.compose.material)
    implementation (libs.logging.interceptor)
    implementation(libs.glide)
    implementation(libs.glide)
    annotationProcessor(libs.compiler)
    implementation(libs.shimmer)
//    implementation("com.github.bumptech.glide:glide:4.15.1")
//    kapt("com.github.bumptech.glide:compiler:4.15.1")
}
