plugins {
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.openapi.generator") version ("7.8.0")
}

repositories {
    mavenCentral()
}

dependencies {
    val jacksonNullableVer = "0.2.6"
    val springdocVer = "2.5.0"
    val guavaVer = "33.3.0-jre"

    implementation("org.openapitools:jackson-databind-nullable:$jacksonNullableVer")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocVer")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation") // For entities validation

    implementation("com.google.guava:guava:$guavaVer") // For hashing

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

sourceSets {
    main {
        java {
            srcDir("$buildDir/generate-resources/main/src/main/java")
        }
    }
}

tasks.openApiGenerate {
    inputSpec.set("$rootDir/user-app/src/main/resources/static/api/swagger.yml")

    generatorName.set("spring")
    library.set("spring-boot")

    apiPackage.set("org.github.scalabletaskmanager.gen.api")
    modelPackage.set("org.github.scalabletaskmanager.gen.model")
    apiNameSuffix.set("API")
    cleanupOutput.set(true)

    additionalProperties.set(
        mapOf(
            "useTags" to "true",
            "interfaceOnly" to "true",
            "useSpringBoot3" to "true",
            "documentationProvider" to "none",
            "annotationLibrary" to "none"
        )
    )
}

tasks.openApiGenerate {
    mustRunAfter("generateEffectiveLombokConfig")
}

tasks.compileJava {
    dependsOn(tasks.openApiGenerate)
}