import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlinx.benchmark.gradle.*
import org.jetbrains.kotlin.allopen.gradle.*

plugins {
    java
    kotlin("jvm") version "1.4.31"
    kotlin("plugin.serialization") version "1.4.31"
    kotlin("plugin.allopen") version "1.4.31"
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
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx.benchmark.runtime-jvm:0.2.0-dev-20")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.1.0")
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")
//    implementation("com.google.code.gson:gson:2.8.6")
//    testImplementation(group = "junit", name = "junit", version = "4.12")
    testImplementation(kotlin("test-junit"))

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
    useIR = true
    jvmTarget = "15"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    useIR = true
    jvmTarget = "15"
}

application {
    mainClass.set("MainKt")
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
            iterationTime = 1000 // time in seconds per iteration
//            mode = "All"
            iterationTimeUnit = "ms" // time unity for iterationTime, default is seconds
        }
    }

    // Setup targets
    targets {
        // This one matches compilation base name, e.g. 'jvm', 'jvmTest', etc

        register("main") {
            this as JvmBenchmarkTarget
            jmhVersion = "1.25.2" // available only for JVM compilations & Java source sets
        }
    }
}