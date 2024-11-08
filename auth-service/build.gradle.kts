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

    implementation(libs.spring.boot.starter.web)
    implementation(libs.lombok)
    implementation(libs.spring.security.core)

    implementation(libs.jooq)
    jooqCodegen(libs.postgresql)
    jooqCodegen(libs.jooq.codegen)
    implementation(libs.jooq.kotlin)

    implementation(libs.postgresql)

    implementation(libs.spring.cloud.starter.config)
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
