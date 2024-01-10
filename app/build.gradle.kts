plugins {
    application
    id("io.freefair.lombok") version "8.4"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(libs.guava)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("pawtropolis.App")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}