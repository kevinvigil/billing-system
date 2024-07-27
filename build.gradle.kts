

val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
	java
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"

	id("dev.monosoul.jooq-docker") version "6.0.28"

	val kotlinVersion = "1.7.22"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion

//	id("org.jooq.jooq-codegen-gradle") version "3.19.10"

//	id("nu.studer.jooq") version "8.0"
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

	// Kotlin
	implementation(enforcedPlatform("org.jetbrains.kotlin:kotlin-bom"))
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

//	jooqCodegen("org.postgresql:postgresql")
	// JOOQ
//	implementation(versionCatalog.findLibrary("jooq").get())
//	jooqCodegen(versionCatalog.findLibrary("jooq-codegen").get())
//	implementation(versionCatalog.findLibrary("jooq-meta").get())
	jooqCodegen(versionCatalog.findLibrary("mysql-connector").get())

	implementation("org.flywaydb:flyway-mysql:10.16.0")

//	implementation("org.postgresql:postgresql")
	implementation("org.flywaydb:flyway-core")

	// Data Base
	implementation(versionCatalog.findLibrary("mysql-connector").get())
//	jooqCodegen("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0") // Spring tries to enforce an old version of this library

}

tasks.test {
	useJUnitPlatform()
}

jooq {
	withContainer {
//		image {
//			name = "mysql:latest"
//			envVars = mapOf(
//				"MYSQL_ROOT_PASSWORD" to "password",
//				"MYSQL_DATABASE" to "billing-system"
//			)
//		}

		db {
			username = "root"
			password = "password"
			name = "billing-system"
			port = 3306
//			uri("jdbc:mysql://localhost:3306/billing-system")

			jdbc {
				schema = "billing-system:mysql"
				driverClassName = "com.mysql.cj.jdbc.Driver"
			}
		}
	}
}
/*
jooq{
	configuration {
		jdbc {
			driver = "com.mysql.cj.jdbc.Driver"
			url = "jdbc:mysql://localhost:3306/billingSystem"
			user = "root"
			password = "password"
		}

		generator {
			database {
				name = "org.jooq.meta.mysql.MySQLDatabase"

				includes = ".*"
			}

			generate {

			}

			target {
				packageName = "com.system.billingsystem.entities.domain"
				directory = "src/main/java"
			}
		}
	}
}

//
//tasks.register<nu.studer.gradle.jooq.JooqGenerate>("generateJooq") {
//	jooq {
//		version.set("3.19.10") // Versión de jOOQ
//		edition.set(nu.studer.gradle.jooq.JooqEdition.OSS) // Open Source Edition
//		group = "jooq"
//		description = "Generates jOOQ classes"
//		inputs.files(file("src/main/resources/db/migration"))
//		outputs.dir(file("src/main/java/com/system/billingsystem/entities"))
//
//
//		configurations {
//			create("main") {
//				jooqConfiguration.apply {
//					logging = org.jooq.meta.jaxb.Logging.WARN
//					jdbc.apply {
//						driver = "com.mysql.cj.jdbc.Driver"
//						url = "jdbc:mysql://localhost:3306/billingSystem?createDatabaseIfNotExist=true"
//						user = "root"
//						password = "password"
//					}
//					generator.apply {
//						name = "org.jooq.codegen.DefaultGenerator"
//						database.apply {
//							name = "org.jooq.meta.mysql.MySQLDatabase"
//							inputSchema = "billingSystem"
//						}
//						generate.apply {
//							isJavaTimeTypes = true
//							isDeprecated = false
//							isRecords = true
//							isImmutablePojos = true
//							isFluentSetters = true
//						}
//						target.apply {
//							packageName = "com.system.billingsystem.entities"
//							directory = "src/main/java"
//						}
//					}
//				}
//			}
//		}
//	}
//}
//
//tasks.named<JavaCompile>("compileJava") {
//	dependsOn(tasks.named("generateJooq"))
//}
//
//tasks.register("generateJooq") {
//	group = "jooq"
//	description = "Generates jOOQ classes"
//	doLast {
//		println("Generating jOOQ classes")
//	}
//}