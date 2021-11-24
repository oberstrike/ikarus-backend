import org.jetbrains.kotlin.builtins.StandardNames.FqNames.annotation
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
val junitJupiterVersion = "5.7.0"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-noarg:1.5.21")
    implementation("io.quarkus:quarkus-jdbc-postgresql")
    implementation("io.quarkus:quarkus-hibernate-validator")
    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    implementation("io.quarkus:quarkus-hibernate-orm-panache-kotlin")
    implementation("io.quarkus:quarkus-resteasy-jackson")
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))

    implementation("com.aventrix.jnanoid:jnanoid:2.0.0")
    implementation("io.quarkus:quarkus-keycloak-authorization")
    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.quarkus:quarkus-arc")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("io.mockk:mockk:${mockkVersion}")
    testImplementation("org.amshove.kluent:kluent:1.68")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testImplementation ("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    testImplementation ("org.testcontainers:testcontainers:1.16.2")
    testImplementation ("org.testcontainers:junit-jupiter:1.16.2")
    testImplementation ("org.testcontainers:postgresql:1.16.2")

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

}

configure<NoArgExtension> {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")

}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    kotlinOptions.javaParameters = true
}
