import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
}

group = "com.forlks"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
//    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-cache")

    implementation("com.github.ben-manes.caffeine:caffeine")
    implementation("com.h2database:h2")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("mysql:mysql-connector-java:8.0.33")
    // https://mvnrepository.com/artifact/com.github.ulisesbocchio/jasypt-spring-boot-starter
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")
    // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api
    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    // https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
    implementation("org.apache.commons:commons-lang3:3.12.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

tasks.getByName<BootBuildImage>("bootBuildImage"){
    imageName = "forlks" + "/${project.name}"
    publish = true
    docker {
        publishRegistry {
            username = ""
            password = ""
        }
    }
}

//tasks.withType<Test> {
//    useJUnitPlatform()
//}
