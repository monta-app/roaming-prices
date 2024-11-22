plugins {
    id("org.jetbrains.kotlin.jvm") version "2.0.21"
    id("org.jetbrains.kotlin.plugin.allopen") version "2.0.21"
    id("com.google.devtools.ksp") version "2.0.21-1.0.28"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.4.4"
    id("io.micronaut.aot") version "4.4.4"
    id("org.jetbrains.kotlin.plugin.jpa") version "2.0.21"
    id("org.flywaydb.flyway") version "9.22.0"
}

version = "0.1"
group = "com.monta.roamingPrices"

val kotlinVersion = project.properties.get("kotlinVersion")
repositories {
    mavenLocal()
    mavenCentral()
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/monta-app/library-micronaut")
        credentials {
            username = System.getenv("GHL_USERNAME") ?: project.findProperty("gpr.user") as String?
            password = System.getenv("GHL_PASSWORD") ?: project.findProperty("gpr.key") as String?
        }
    }
    maven {
        url = uri("https://packages.confluent.io/maven")
    }
}

dependencies {
    runtimeOnly("org.yaml:snakeyaml")
    ksp("io.micronaut:micronaut-http-validation")
    ksp("io.micronaut.serde:micronaut-serde-processor")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    compileOnly("io.micronaut:micronaut-http-client")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("net.logstash.logback:logstash-logback-encoder:8.0")

    implementation("io.micronaut:micronaut-inject-java")
    // Micronaut dependencies
    implementation("io.micronaut:micronaut-runtime:4.4.4")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari:6.0.2")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa:4.4.4")
    implementation("io.micronaut.data:micronaut-data-jdbc:4.4.4")
    implementation("io.micronaut.flyway:micronaut-flyway:4.4.4")

    // Database drivers
    runtimeOnly("mysql:mysql-connector-java:8.0.33")
    runtimeOnly("org.flywaydb:flyway-mysql")

    // Java annotations
    implementation("javax.annotation:javax.annotation-api")


    // Test dependencies
    testImplementation("org.testcontainers:testcontainers:1.19.0")
    testImplementation("org.testcontainers:mysql:1.19.0")
    testImplementation("io.micronaut:micronaut-http-client")
    testImplementation("io.micronaut.test:micronaut-test-kotest5:4.4.4")
    testImplementation("io.kotest:kotest-runner-junit5:5.6.2")
    testImplementation("io.kotest:kotest-assertions-core:5.6.2")
}


application {
    mainClass = "com.monta.roamingPrices.ApplicationKt"
}
java {
    sourceCompatibility = JavaVersion.toVersion("21")
}


graalvmNative.toolchainDetection = false

micronaut {
    runtime("netty")
    testRuntime("kotest5")
    processing {
        incremental(true)
        annotations("com.monta.roamingPrices.*")
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }
}
micronaut {
    aot {
        optimizeServiceLoading = false
        precomputeOperations = false
    }
}

tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "21"
}

tasks.withType<ProcessResources> {
    from("src/main/resources")
}
tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
        }
    }

    withType<com.google.devtools.ksp.gradle.KspTaskJvm> {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
        }
    }
}

java {
    sourceCompatibility = JavaVersion.toVersion("21")
    targetCompatibility = JavaVersion.toVersion("21")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}