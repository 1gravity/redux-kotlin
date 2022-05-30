buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        google()
    }

    dependencies {
        classpath(Android.tools.build.gradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
        classpath("org.jetbrains.kotlinx:atomicfu-gradle-plugin:_")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:_")
    }
}

allprojects {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        google()
    }

    afterEvaluate {
        // eliminate log pollution until Android support for KMM improves
        val sets2BeRemoved = setOf(
            "androidAndroidTestRelease",
            "androidTestFixtures",
            "androidTestFixturesDebug",
            "androidTestFixturesRelease"
        )
        project.extensions.findByType<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension>()?.let { kmpExt ->
            kmpExt.sourceSets.removeAll { sets2BeRemoved.contains(it.name) }
        }
    }

    group = project.properties["POM_GROUP"]!!
    version = project.properties["POM_VERSION_NAME"]!!
    if (hasProperty("SNAPSHOT") || System.getenv("SNAPSHOT") != null) {
        version = "$version-SNAPSHOT"
    }
}
