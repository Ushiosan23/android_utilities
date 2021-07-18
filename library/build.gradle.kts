plugins {
	signing
	`maven-publish`
	id("com.android.library")
	id("kotlin-android")
	id("org.jetbrains.dokka")
}

// Apply script
apply("../local.gradle.kts")

// Extra properties
val extra = rootProject.extra

/* Android configuration */
android {
	// Global configurations
	compileSdkVersion(extra["sdkVersion"] as Int)
	buildToolsVersion(extra["buildToolsVersion"] as String)

	// Default configuration
	defaultConfig {
		targetSdkVersion(extra["sdkVersion"] as Int)
		minSdkVersion(extra["sdkMinVersion"] as Int)

		versionCode(extra["versionCode"] as Int)
		versionName(extra["versionName"] as String)

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

tasks.create("androidSourceJar", Jar::class) {
	archiveClassifier.set("sources")
	if (project.plugins.hasPlugin("com.android.library")) {
		from(android.sourceSets["main"].java.srcDirs)
		from(android.sourceSets["main"].jni.srcDirs)
	} else {
		from(sourceSets["main"].java.srcDirs)
	}
}

tasks.create("dokkaPackageJar", Jar::class) {
	archiveClassifier.set("javadoc")
	if (project.plugins.hasPlugin("org.jetbrains.dokka")) {
		from(project.tasks["dokkaHtml"])
		dependsOn(project.tasks["dokkaHtml"])
	}
}

/* MavenCentral configuration */
afterEvaluate {
	publishing {
		publications {
			create<MavenPublication>("release") {
				groupId = rootProject.extra["maven.groupId"] as String
				artifactId = rootProject.extra["maven.artifactId"] as String
				version = rootProject.extra["versionName"] as String

				from(components["release"])
				artifact(project.tasks["androidSourceJar"])
				artifact(project.tasks["dokkaPackageJar"])

				// Pom file
				pom {
					name.set(rootProject.extra["maven.artifactId"] as String)
					description.set(rootProject.extra["maven.artifactDescription"] as String)
					url.set(rootProject.extra["maven.artifactUrl"] as String)

					licenses {
						license {
							name.set("MIT")
							url.set("${rootProject.extra["maven.artifactUrl"]}/blob/main/LICENSE.md")
						}
					}
					developers {
						developer {
							id.set("Ushiosan23")
							name.set("Brian Alvarez")
							email.set("haloleyendee@outlook.com")
						}
					}

					scm {
						connection.set(rootProject.extra["scm.connection"] as String)
						developerConnection.set(rootProject.extra["scm.connection.ssh"] as String)
						url.set(rootProject.extra["scm.url"] as String)
					}

				}
			}
		}
	}
}

/* Signing configuration */
afterEvaluate {
	signing {
		sign(publishing.publications)
	}
}

/* library dependencies */
dependencies {
	// Custom dependencies
	implementation(kotlin("stdlib"))

	// Android dependencies
	implementation("androidx.core:core-ktx:1.6.0")
	implementation("androidx.appcompat:appcompat:1.3.0")
	implementation("com.google.android.material:material:1.4.0")

	// Jetpack dependencies
	implementation("androidx.biometric:biometric:1.1.0")

	// Test implementations
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.3")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
