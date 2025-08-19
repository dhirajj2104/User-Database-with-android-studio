plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.database"
    compileSdk = 35  // 🔹 Update this from 34 to 35

    defaultConfig {
        applicationId = "com.example.loginwithsqlite"
        minSdk = 24
        targetSdk = 35  // 🔹 Update this from 34 to 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}