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

	versionCatalog.findLibrary("spring-boot-starter-test").ifPresent {
		testCompileOnly(it)
		testRuntimeOnly(it)
	}
	versionCatalog.findLibrary("spring-security-test").ifPresent {
		testCompileOnly(it)
		testRuntimeOnly(it)
	}

	versionCatalog.findLibrary("jetbrains-annotation").ifPresent { implementation(it) }
	versionCatalog.findLibrary("lombok").ifPresent {
		compileOnly(it)
		annotationProcessor(it)
	}

	versionCatalog.findLibrary("mysql-connector").ifPresent { runtimeOnly(it) }

	versionCatalog.findLibrary("junit-platform-launcher").ifPresent { testRuntimeOnly(it) }

	versionCatalog.findLibrary("zalando").ifPresent { implementation(it) }
	versionCatalog.findLibrary("problem-spring-web-starter").ifPresent { implementation(it) }
	versionCatalog.findLibrary("problem-spring-web").ifPresent { implementation(it) }
	versionCatalog.findLibrary("jackson-datatype-problem").ifPresent { implementation(it) }

}

tasks.withType<Test> {
	useJUnitPlatform()
}
