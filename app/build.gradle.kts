plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "com.example.healthhub"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.healthhub"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // ANDROIDX
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // COMPOSE
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)

    // KOIN
    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    // MODULES
    implementation(project(":data:account"))
    implementation(project(":data:appointments"))
    implementation(project(":data:authentication"))
    implementation(project(":data:home"))
    implementation(project(":data:info"))
    implementation(project(":data:medicalfile"))
    implementation(project(":data:util"))
    implementation(project(":domain:account"))
    implementation(project(":domain:provider"))
    implementation(project(":feature:account"))
    implementation(project(":feature:appointments"))
    implementation(project(":feature:authentication"))
    implementation(project(":feature:home"))
    implementation(project(":feature:info"))
    implementation(project(":feature:medicalfile"))
    implementation(project(":network"))
    implementation(project(":ui:catalog"))
    implementation(project(":ui:navigation"))

    // NAVIGATION
    implementation(libs.navigation.compose)

    // NETWORK
    implementation(libs.okhttp3)

    // TEST
    androidTestImplementation(libs.test.espresso.core)
    androidTestImplementation(libs.test.ext)
    testImplementation(libs.junit)
}