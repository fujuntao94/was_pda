plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.sobuy.pda"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.sobuy.pda"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        flavorDimensions("environment")

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    viewBinding {
        enable = true
    }

    buildFeatures {
        buildConfig = true
    }

    dataBinding {
        enable = true
    }

    productFlavors {
        create("dev") {
            // 开发环境配置
            dimension = "environment"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            resValue("string", "app_name", "MyApp Dev")
            buildConfigField("String", "API_URL", "\"http://106.14.57.78:18086/api/\"")
        }

        create("prod") {
            // 生产环境配置
            dimension = "environment"
            applicationIdSuffix = ""
            versionNameSuffix = ""
            resValue("string", "app_name", "MyApp")
            buildConfigField("String", "API_URL", "\"https://api.example2.com\"")
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.permissionx)
    implementation(project(":super-k"))
    implementation(libs.mmkv)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.converter.gson)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.rxandroidble)
    implementation(libs.tablayout.v371)
    implementation(libs.viewpager2delegate.v371)
    implementation(libs.androidx.cardview)
    implementation(libs.qmui)
    implementation("com.google.android.flexbox:flexbox:3.0.0")
    // 基础依赖包，必须要依赖
    implementation(libs.immersionbar.v321)
    // kotlin扩展（可选）
    implementation(libs.immersionbar.ktx.v321)
    // fragment快速实现（可选）
    implementation(libs.immersionbar.components)
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.legacy.support.v4)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}