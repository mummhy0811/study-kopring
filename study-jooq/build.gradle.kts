plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("plugin.jpa") version "1.9.25"
	id("nu.studer.jooq") version "8.2"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
	jooqGenerator {
		extendsFrom(configurations.runtimeClasspath.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	runtimeOnly("org.postgresql:postgresql")
	jooqGenerator("org.postgresql:postgresql:42.6.0")
	jooqGenerator("org.jooq:jooq:3.18.2") // 호환되는 jOOQ 버전
	jooqGenerator("org.jooq:jooq-meta:3.18.2")
	jooqGenerator("org.jooq:jooq-codegen:3.18.2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jooq {
	version.set("3.18.2") // jOOQ 버전
	configurations {
		create("main") {
			generateSchemaSourceOnCompilation.set(true) // 컴파일 시 자동 코드 생성
			jooqConfiguration.apply {
				jdbc.apply {
					driver = "org.postgresql.Driver"
					url = "jdbc:postgresql://localhost:5432/prac" // 본인의 DB 설정에 맞춤
					user = "moonhnxe"
					password = "0811"
				}
				generator.apply {
					name = "org.jooq.codegen.KotlinGenerator" // Kotlin 코드를 생성하도록 지정
					database.apply {
						name = "org.jooq.meta.postgres.PostgresDatabase"
						inputSchema = "public"
					}
					generate.apply {
						withRecords(true)
						withImmutablePojos(true)
						withFluentSetters(true)
						withJavaTimeTypes(true) // Java Time 사용
					}
					target.apply {
						packageName = "com.example.jooq.tables" // 생성될 패키지 이름
						directory = "src/generated/jooq" // 생성될 파일 경로
					}
				}
			}
		}
	}
}
