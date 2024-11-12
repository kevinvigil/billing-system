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
        languageVersion = JavaLanguageVersion.of(22)
    }
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(libs.spring.boot.starter.security)
    implementation(libs.problem.spring.web.starter)
    implementation(libs.problem.spring.web)
    implementation(libs.jackson.datatype.problem)
    implementation(libs.jackson.datatype.jsr310)

    implementation(libs.spring.boot.devtools)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.security.core)

    implementation(libs.spring.boot.starter.jooq)
    implementation(libs.jooq.kotlin)
    implementation(libs.jooq)
    jooqCodegen(libs.jooq.codegen)
    jooqCodegen(libs.postgresql)

    implementation(libs.postgresql)

    implementation(libs.spring.cloud.starter.config)
    implementation(libs.spring.cloud.starter.bootstrap)
//    implementation(libs.spring.cloud.starter.netflix.eureka.client)

    implementation(libs.lombok)
    annotationProcessor(libs.lombok)
    implementation(libs.mapstruct)
    annotationProcessor(libs.mapstruct.processor)
    annotationProcessor(libs.lombok.mapstruct.binding)
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
        doLast {
            println (" \n generateJooqClasses ha terminado de ejecutarse. \n")
        }
    }
}
