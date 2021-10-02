plugins {
	id("com.android.application")
	id("kotlin-android")
}

// Extra properties
val extra = rootProject.extra

/* Android configuration */
android {
	// Global configurations
	compileSdk = extra["sdkVersion"] as Int
	buildToolsVersion = extra["buildToolsVersion"] as String

	// Default configuration
	defaultConfig {
		minSdk = extra["sdkMinVersion"] as Int
		targetSdk = extra["sdkVersion"] as Int

		versionCode = extra["versionCode"] as Int
		versionName = extra["versionName"] as String

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	// Build configurations
	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				file("proguard-rules.pro")
			)
		}
	}

	// Android features
	buildFeatures {
		viewBinding = true
	}

	// Jdk configuration
	compileOptions {
		sourceCompatibility = extra["sourceCompatibility"] as JavaVersion
		targetCompatibility = extra["sourceCompatibility"] as JavaVersion
	}

	// Kotlin configuration
	kotlinOptions {
		jvmTarget = extra["jvmTarget"] as String
	}
}

/* library dependencies */
dependencies {
	// Custom dependencies
	implementation(kotlin("stdlib"))
	implementation(project(":library"))

	// Android dependencies
	implementation("androidx.core:core-ktx:1.6.0")
	implementation("androidx.appcompat:appcompat:1.3.1")
	implementation("com.google.android.material:material:1.4.0")

	// Jetpack dependencies

	// Test implementations
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.3")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
