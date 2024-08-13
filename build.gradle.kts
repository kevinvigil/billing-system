val catalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
	java
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
	id("dev.monosoul.jooq-docker") version "6.0.28"
}

group = "com.system"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {

	implementation(catalog.findLibrary("spring-boot-starter-jooq").get())
	implementation(catalog.findLibrary("spring-boot-starter-web").get())

	implementation(catalog.findLibrary("problem-spring-web-starter").get())
	implementation(catalog.findLibrary("problem-spring-web").get())
	implementation(catalog.findLibrary("jackson-datatype-problem").get())
	implementation(catalog.findLibrary("jackson-datatype-jsr310").get())
	implementation(catalog.findLibrary("jetbrains-annotation").get())


	// Development
	developmentOnly(catalog.findLibrary("spring-boot-devtools").get())

	// Testing
	testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
	testImplementation("org.junit.platform:junit-platform-suite:1.10.3")

	testImplementation(catalog.findLibrary("testcontainers").get())
	testImplementation(catalog.findLibrary("testcontainers-junit-jupiter").get())
	testImplementation(catalog.findLibrary("testcontainers-postgresql").get())
	testImplementation(catalog.findLibrary("spring-cloud-contract-wiremock").get())


	testImplementation(catalog.findLibrary("spring-boot-starter-test").get()) {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
//	testImplementation( "spring-security-test")


	testImplementation(catalog.findLibrary( "junit-platform-runner").get())

	// Compile and annotations
	compileOnly(catalog.findLibrary( "lombok").get())
	annotationProcessor(catalog.findLibrary( "lombok").get())

	// JOOQ
	implementation("org.jooq:jooq-kotlin:3.19.10")
	implementation(catalog.findLibrary( "jooq").get())
	jooqCodegen(catalog.findLibrary( "jooq-codegen").get())
	jooqCodegen("org.postgresql:postgresql:42.7.3")

	// MySql
	implementation("org.postgresql:postgresql:42.7.3")

	// JWT
	implementation(catalog.findLibrary("spring-boot-starter-security").get())

	implementation("org.springframework.boot:spring-boot-starter-mail:3.3.0")

	implementation("io.jsonwebtoken:jjwt-api:0.12.6")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")

	// https://mvnrepository.com/artifact/com.google.code.gson/gson
	implementation("com.google.code.gson:gson:2.11.0")


	implementation("com.vaadin.external.google:android-json:0.0.20131108.vaadin1")

	implementation("org.apache.pdfbox:pdfbox:2.0.32")

}

tasks.test {
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