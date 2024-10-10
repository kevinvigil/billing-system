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
		languageVersion = JavaLanguageVersion.of(23)
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
	implementation("org.springframework.boot:spring-boot-starter-webflux:3.3.0")

	implementation(catalog.findLibrary("spring-boot-starter-jooq").get())
	implementation(catalog.findLibrary("spring-boot-starter-web").get())

	implementation(catalog.findLibrary("problem-spring-web-starter").get())
	implementation(catalog.findLibrary("problem-spring-web").get())

	implementation(catalog.findLibrary("jackson-datatype-problem").get())
	implementation(catalog.findLibrary("jackson-datatype-jsr310").get())


	implementation(catalog.findLibrary("jetbrains-annotation").get())

	developmentOnly(catalog.findLibrary("spring-boot-devtools").get())

	// Testing
	testImplementation(catalog.findLibrary( "junit-platform-runner").get())
	testImplementation(catalog.findLibrary("junit-jupiter").get())

	testImplementation(catalog.findLibrary("spring-boot-starter-test").get()) {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
//	testImplementation( "spring-security-test")

	testImplementation(catalog.findLibrary("reactor-test").get())

	// Testcontainers
	testImplementation(catalog.findLibrary("testcontainers").get())
	testImplementation(catalog.findLibrary("testcontainers-junit-jupiter").get())
	testImplementation(catalog.findLibrary("testcontainers-postgresql").get())

	// WireMock
	implementation(catalog.findLibrary("spring-cloud-contract-wiremock").get())

	// mapper
	implementation (catalog.findLibrary("mapstruct").get())
	annotationProcessor (catalog.findLibrary("mapstruct-processor").get())

	implementation(catalog.findLibrary( "lombok").get())
	annotationProcessor(catalog.findLibrary( "lombok").get())
	annotationProcessor(catalog.findLibrary("lombok-mapstruct-binding").get())

	// JOOQ
	implementation(catalog.findLibrary( "jooq-kotlin").get())
	implementation(catalog.findLibrary( "jooq").get())
	jooqCodegen(catalog.findLibrary( "jooq-codegen").get())
	jooqCodegen(catalog.findLibrary("postgresql").get())

	// Postgresql
	implementation(catalog.findLibrary("postgresql").get())

	// JWT
	implementation(catalog.findLibrary("spring-boot-starter-security").get())

	implementation(catalog.findLibrary("spring-boot-starter-mail").get())

	implementation(catalog.findLibrary("jjwt-api").get())
	runtimeOnly(catalog.findLibrary("jjwt-impl").get())
	runtimeOnly(catalog.findLibrary("jjwt-jackson").get())

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