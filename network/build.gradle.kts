plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.healthhub.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // ANDROIDX
    implementation(libs.androidx.core.ktx)

    // KOIN
    implementation(libs.koin.android)

    // NETWORK
    implementation(libs.moshi.kotlin)
    implementation(libs.okhttp3)
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.converter.gson)

    // TEST
    androidTestImplementation(libs.test.espresso.core)
    androidTestImplementation(libs.test.ext)
    testImplementation(libs.junit)
}