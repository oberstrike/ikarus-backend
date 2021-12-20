import org.jetbrains.kotlin.noarg.gradle.NoArgExtension

plugins {
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.allopen") version "1.5.21"
    id("io.quarkus")
    id("org.jetbrains.kotlin.plugin.noarg") version "1.5.21"
}

repositories {
    mavenCentral()
    mavenLocal()
}


val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project
val mockkVersion = "1.12.1"
val junitJupiterVersion = "5.4.2"
val kluentVersion = "1.68"
val testcontainersVersion = "1.16.2"
val kotlinFakerVersion = "1.9.0"
val kotlinVersion = "1.5.21"
val jnanoid = "2.0.0"

dependencies {
    //Quarkus dependencies
    implementation("io.quarkus:quarkus-jdbc-postgresql")
    implementation("io.quarkus:quarkus-hibernate-validator")
    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    implementation("io.quarkus:quarkus-hibernate-orm-panache-kotlin")
    implementation("io.quarkus:quarkus-resteasy-jackson")
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-keycloak-authorization")
    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-scheduler")
    implementation("io.quarkus:quarkus-keycloak-admin-client")
    implementation("io.quarkus:quarkus-resteasy-multipart")
    implementation("io.quarkus:quarkus-rest-client")

    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")

    //other dependencies
    implementation("org.jetbrains.kotlin:kotlin-noarg:$kotlinVersion")
    implementation("com.aventrix.jnanoid:jnanoid:$jnanoid")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
    testImplementation("io.quarkus:quarkus-test-security-oidc")

    // https://mvnrepository.com/artifact/commons-io/commons-io
    implementation("commons-io:commons-io:2.11.0")

    // https://mvnrepository.com/artifact/io.rest-assured/kotlin-extensions
    testImplementation("io.rest-assured:kotlin-extensions:4.4.0")

    //test dependencies
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("io.quarkiverse.mockk:quarkus-junit5-mockk:0.2.0")
    testImplementation("io.quarkus:quarkus-test-keycloak-server")
    testImplementation("org.amshove.kluent:kluent:$kluentVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    testImplementation("org.testcontainers:testcontainers:$testcontainersVersion")
    testImplementation("org.testcontainers:junit-jupiter:$testcontainersVersion")
    testImplementation("org.testcontainers:postgresql:$testcontainersVersion")
    testImplementation("io.github.serpro69:kotlin-faker:$kotlinFakerVersion")
    testImplementation("com.github.dasniko:testcontainers-keycloak:1.8.1")


}
group = "de.ma.ikarus"
version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")

}

configure<NoArgExtension> {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")

}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    kotlinOptions.javaParameters = true
}

task("example") {
    doLast {

    }
}
