import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    java
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
    id("kotlinx.benchmark") version "0.2.0-dev-20"
    application
}

group = "me.enot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://kotlin.bintray.com/kotlinx/")
}
dependencies {

    val kotlinVersion = "1.4.10"
    val coroutines = "1.3.9"
    implementation("org.jetbrains.kotlinx:kotlinx.benchmark.runtime-jvm:0.2.0-dev-20")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0-RC2")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.1.0")
//    implementation("com.google.code.gson:gson:2.8.6")
//    testImplementation(group = "junit", name = "junit", version = "4.12")
    testImplementation(kotlin("test-junit"))

}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}

benchmark {
    // Create configurations
    configurations {

        getByName("main") { // main configuration is created automatically, but you can change its defaults
            warmups = 10 // number of warmup iterations
            iterations = 10 // number of iterations
            iterationTime = 5 // time in seconds per iteration
        }
        create("smoke") {
            warmups = 5 // number of warmup iterations
            iterations = 5 // number of iterations
            iterationTime = 1 // time in seconds per iteration
//            iterationTimeUnit = "ms" // time unity for iterationTime, default is seconds
        }
    }

    // Setup targets
    targets {
        // This one matches compilation base name, e.g. 'jvm', 'jvmTest', etc

        register("main") {
            (this as kotlinx.benchmark.gradle.JvmBenchmarkTarget).jmhVersion = "1.25.2" // available only for JVM compilations & Java source sets
        }
    }
}