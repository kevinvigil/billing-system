val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

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

	implementation(versionCatalog.findLibrary("spring-boot-starter-jooq").get())
	implementation(versionCatalog.findLibrary("spring-boot-starter-security").get())
	implementation(versionCatalog.findLibrary("spring-boot-starter-web").get())

	implementation(versionCatalog.findLibrary("problem-spring-web-starter").get())
	implementation(versionCatalog.findLibrary("problem-spring-web").get())
	implementation(versionCatalog.findLibrary("jackson-datatype-problem").get())
	implementation(versionCatalog.findLibrary("jackson-datatype-jsr310").get())
	implementation(versionCatalog.findLibrary("jetbrains-annotation").get())


	// Development
	developmentOnly(versionCatalog.findLibrary("spring-boot-devtools").get())

	// Testing
	testImplementation(versionCatalog.findLibrary("spring-boot-starter-test").get())
	testImplementation(versionCatalog.findLibrary("spring-security-test").get())


	testImplementation(versionCatalog.findLibrary("junit-platform-runner").get())
	testImplementation(versionCatalog.findLibrary("junit-jupiter-api").get())
	testRuntimeOnly (versionCatalog.findLibrary("junit-jupiter-engine").get())

	testImplementation(versionCatalog.findLibrary("h2-dataBase").get())

	// Compile and annotations
	compileOnly(versionCatalog.findLibrary("lombok").get())
	annotationProcessor(versionCatalog.findLibrary("lombok").get())

	// JOOQ
	implementation("org.jooq:jooq-kotlin:3.19.10")
	implementation(versionCatalog.findLibrary("jooq").get())
	jooqCodegen(versionCatalog.findLibrary("jooq-codegen").get())
	jooqCodegen("org.postgresql:postgresql:42.7.3")

	implementation("org.postgresql:postgresql:42.7.3")

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