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
val rpExtra get() = rootProject.extra

/* Android configuration */
android {
	// Global configurations
	compileSdk = rpExtra["sdkVersion"] as Int
	buildToolsVersion = rpExtra["buildToolsVersion"] as String

	// Default configuration
	defaultConfig {
		minSdk = rpExtra["sdkMinVersion"] as Int
		targetSdk = rpExtra["sdkVersion"] as Int

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
		sourceCompatibility = rpExtra["sourceCompatibility"] as JavaVersion
		targetCompatibility = rpExtra["sourceCompatibility"] as JavaVersion
	}
}

tasks.dokkaHtml.configure {
	suppressObviousFunctions.set(true)
	dokkaSourceSets {
		configureEach {
			jdkVersion.set((rpExtra["jvmTarget"] as String).toInt())
		}
	}
}

@Suppress("DEPRECATION")
tasks.create("androidSourceJar", Jar::class) {
	archiveClassifier.set("sources")
	if (project.plugins.hasPlugin("com.android.library")) {
		from(android.sourceSets["main"].java.srcDirs)
		from(android.sourceSets["main"].jni.srcDirs)
	} else {
		from(sourceSets["main"].java.srcDirs)
	}
}

tasks.create("documentationPackageJar", Jar::class) {
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
				artifact(project.tasks["documentationPackageJar"])

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
	// Android dependencies
	implementation("androidx.core:core-ktx:1.7.0")
	implementation("androidx.appcompat:appcompat:1.4.1")
	implementation("androidx.activity:activity-ktx:1.4.0")
	implementation("com.google.android.material:material:1.5.0")
	implementation("androidx.appcompat:appcompat-resources:1.4.1")

	// Jetpack dependencies
	implementation("androidx.biometric:biometric:1.1.0")
	implementation("androidx.preference:preference-ktx:1.2.0")

	// Test implementations
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.3")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
