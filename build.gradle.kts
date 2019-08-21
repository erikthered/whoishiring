plugins {
    base
    kotlin("jvm") version "1.3.41"
}

val spekVersion by extra("2.0.6")

allprojects {
    repositories {
        jcenter()
    }
}

subprojects {
    apply(plugin = "kotlin")

    dependencies {
        "implementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        "implementation"("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.+")
        "implementation"("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.9.+")
        "testImplementation"("org.jetbrains.kotlin:kotlin-test")
        "testImplementation"("org.jetbrains.kotlin:kotlin-test-junit")
        "testImplementation"("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
        "testRuntimeOnly"("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
        "testRuntimeOnly"("org.jetbrains.kotlin:kotlin-reflect")
    }
}