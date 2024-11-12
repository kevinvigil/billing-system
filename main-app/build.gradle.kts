val catalog = versionCatalogs.named("libs")

plugins {
    java
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    id("dev.monosoul.jooq-docker") version "6.0.28"
}

group = "com.billingsystem"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

repositories {
    mavenCentral()
}

//dependencies {
//    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
//}


dependencies {

    implementation("org.springframework.boot:spring-boot-starter")

//    implementation("org.springframework.boot:spring-boot-starter-webflux:3.3.0")

    implementation(libs.spring.boot.starter.web)

    implementation( libs.problem.spring.web.starter )
    implementation( libs.problem.spring.web )

    implementation( libs.jackson.datatype.problem )
    implementation( libs.jackson.datatype.jsr310 )


    implementation( libs.jetbrains.annotation )

    developmentOnly( libs.spring.boot.devtools )

    // Testing
    testImplementation( libs.junit.platform.runner )
    testImplementation( libs.junit.jupiter )

    testImplementation( libs.spring.boot.starter.test ) {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
//	testImplementation( "spring.security.test")

    testImplementation(libs.reactor.test)

    // Testcontainers
    testImplementation(libs.testcontainers)
    testImplementation(libs.testcontainers.junit.jupiter)
    testImplementation(libs.testcontainers.postgresql)

    // WireMock
    implementation(libs.spring.cloud.contract.wiremock)

    // mapper
    implementation(libs.mapstruct)
    annotationProcessor(libs.mapstruct.processor)
    annotationProcessor(libs.lombok.mapstruct.binding)

    implementation(libs.lombok)
    annotationProcessor(libs.lombok)

    // JOOQ
    implementation(libs.spring.boot.starter.jooq)
    implementation(libs.jooq.kotlin)
    implementation(libs.jooq)
    jooqCodegen(libs.jooq.codegen)
    jooqCodegen(libs.postgresql)

    // Postgresql
    implementation(libs.postgresql)

    implementation(libs.spring.boot.starter.security)

    implementation(libs.spring.cloud.starter.config)
    implementation(libs.spring.cloud.starter.bootstrap)
//    implementation(libs.spring.cloud.starter.netflix.eureka.client)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks {
    generateJooqClasses {
        schemas.set(listOf("public"))
        basePackageName.set("domain")
        migrationLocations.setFromFilesystem(
            project.files("$projectDir/src/main/resources/db/migration"),
        )
    }
}