plugins {
	id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

buildscript {
	// Android properties
	extra.set("sdkVersion", 30)
	extra.set("sdkMinVersion", 23)
	extra.set("buildToolsVersion", "31.0.0")

	// Library properties
	extra.set("versionCode", 1)
	extra.set("versionName", "1.2.0")

	// JDK properties
	extra.set("jvmTarget", "11")
	extra.set("sourceCompatibility", JavaVersion.VERSION_11)

	// Maven configuration
	extra.set("maven.groupId", "com.github.ushiosan23")
	extra.set("maven.artifactId", "android_utilities")
	extra.set("maven.artifactDescription", "Android Utilities")
	extra.set("maven.artifactUrl", "https://github.com/Ushiosan23/android_utilities")

	// Git configuration
	extra.set("scm.connection", "scm:git:github.com/Ushiosan23/android_utilities.git")
	extra.set("scm.connection.ssh", "scm:git:ssh:github.com/Ushiosan23/android_utilities.git")
	extra.set("scm.url", "${extra["maven.artifactUrl"]}/tree/main")

	// Repositories
	repositories {
		google()
		mavenCentral()
	}

	// Plugin dependencies
	dependencies {
		classpath("com.android.tools.build:gradle:7.0.4")
		classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.5.0")
		classpath(kotlin("gradle-plugin", "1.5.31"))
	}
}

allprojects {
	// All projects repositories
	repositories {
		google()
		mavenCentral()
	}
}

nexusPublishing {
	repositories {
		sonatype {
			apply("local.gradle.kts")

			stagingProfileId.set(rootProject.extra["sonatype.profileId"] as String)
			username.set(rootProject.extra["sonatype.username"] as String)
			password.set(rootProject.extra["sonatype.password"] as String)
		}
	}
}
