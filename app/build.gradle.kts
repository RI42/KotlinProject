import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.allopen.gradle.*


plugins {
    val kotlin = "1.5.31"
    java
    kotlin("jvm") version kotlin
    kotlin("plugin.serialization") version kotlin
    kotlin("plugin.allopen") version kotlin
    id("org.jetbrains.kotlinx.benchmark") version "0.3.1"
    application
}

group = "me.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://kotlin.bintray.com/kotlinx/")
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))

    val coroutines = "1.5.2"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")

    implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime-jvm:0.3.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.2.1")
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")
    implementation("com.google.code.gson:gson:2.8.8")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines")

}

tasks.test {
    useJUnit()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
//    freeCompilerArgs = listOf("-Xjvm-default=all")
    jvmTarget = "16"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "16"
}

configure<AllOpenExtension> {
    annotation("org.openjdk.jmh.annotations.State")
}

benchmark {
    // Create configurations
    configurations {

        getByName("main") { // main configuration is created automatically, but you can change its defaults
            warmups = 10 // number of warmup iterations
            iterations = 10 // number of iterations
            iterationTime = 5000 // time in seconds per iteration
            iterationTimeUnit = "ms"
        }
        create("smoke") {
            warmups = 5 // number of warmup iterations
            iterations = 5 // number of iterations
            iterationTime = 5000 // time in seconds per iteration
//            mode = "All"
            iterationTimeUnit = "ms" // time unity for iterationTime, default is seconds
        }
    }

    // Setup targets
    targets {
        // This one matches compilation base name, e.g. 'jvm', 'jvmTest', etc

        register("main") {
            this as kotlinx.benchmark.gradle.JvmBenchmarkTarget
            jmhVersion = "1.33" // available only for JVM compilations & Java source sets
        }
    }
}

application {
    mainClass.set("AppKt")
}