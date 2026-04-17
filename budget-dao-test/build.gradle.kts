plugins {
    alias(libs.plugins.kotlinJvm)
    `java-library`
}

group = "bps"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/benjishults/console")
        credentials {
            username = providers
                .gradleProperty("github.actor")
                .getOrElse(System.getenv("GITHUB_ACTOR"))
            password = providers
                .gradleProperty("github.token")
                .getOrElse(System.getenv("GITHUB_TOKEN"))
        }
    }
}

kotlin {
    jvmToolchain(23)
    compilerOptions {
        optIn.add("kotlin.time.ExperimentalTime")
        apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_3)
        languageVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_3)
    }
}

dependencies {

    api(project(":budget-dao"))
    // TODO see how many of these I can get rid of
    api(libs.bps.app.config)
    api(libs.konf)
    api(libs.commons.validator)
    api(libs.postgres)
//    api(libs.kotlinx.datetime)
    api(libs.jackson.jsr310)
    api(libs.jackson.jdk8)
    api(libs.jackson.yaml)
    api(libs.jackson.kotlin) {
        exclude(group = "org.jetbrains.kotlin")
    }

    implementation(libs.mockk.jvm)
    implementation(libs.kotest.junit5)
    implementation(libs.kotest.assertions.core)
    implementation(libs.junit.jupiter)
}
