import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.devtoolsKsp)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.kryptopass.movie"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kryptopass.movie"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        lateinit var properties: Properties
        var tmdbApiKey = ""
        var tmdbBaseUrl = ""
        var tmdbImageUrl = ""

        if (File("local.properties").exists()) {
            properties =
                Properties().apply {
                    load(project.rootProject.file("local.properties").inputStream())
                }
            tmdbApiKey = properties.getProperty("TMDB_API_KEY")
            tmdbBaseUrl = properties.getProperty("TMDB_BASE_URL")
            tmdbImageUrl = properties.getProperty("TMDB_IMAGE_URL")
        } else {
            System.getenv("TMDB_API_KEY")
            System.getenv("TMDB_BASE_URL")
            System.getenv("TMDB_IMAGE_URL")
        }

        buildConfigField("String", "TMDB_API_KEY", "\"$tmdbApiKey\"")
        buildConfigField("String", "TMDB_BASE_URL", "\"$tmdbBaseUrl\"")
        buildConfigField("String", "TMDB_IMAGE_URL", "\"$tmdbImageUrl\"")
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
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.compose)
    implementation(libs.hilt.android)
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit)

    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.robolectric)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.test.manifest)
    debugImplementation(libs.androidx.ui.tooling)
}
