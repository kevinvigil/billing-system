val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
	java
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
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
	versionCatalog.findLibrary("spring-boot-starter-data-jpa").ifPresent { implementation(it)}
	versionCatalog.findLibrary("spring-boot-starter-security").ifPresent { implementation(it) }
	versionCatalog.findLibrary("spring-boot-starter-web").ifPresent { implementation(it) }
	versionCatalog.findLibrary("spring-boot-devtools").ifPresent { implementation(it) }
	versionCatalog.findLibrary("spring-boot-starter-test").ifPresent { implementation(it) }
	versionCatalog.findLibrary("security:spring-security-test").ifPresent { implementation(it) }

    implementation("org.jetbrains:annotations:24.0.0")
    compileOnly("org.projectlombok:lombok")
//	runtimeOnly("com.h2database:h2")
	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
