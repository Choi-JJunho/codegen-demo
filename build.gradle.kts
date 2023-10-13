import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    id("org.openapi.generator") version "6.6.0"
}

group = "com.example"
val projectPackageName = "com.example.demo"
val projectPackagePath = "com/example/demo"
val contractFileName = "campus-platform-contract.yaml"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("$rootDir/contract/$contractFileName")
    outputDir.set("$buildDir/openapi")
    apiPackage.set("$projectPackageName.api")
    invokerPackage.set("$projectPackageName.invoker")
    modelPackage.set("$projectPackageName.model")
    configOptions.set(
        mapOf(
            "dateLibrary" to "kotlin-spring",
            "useSpringBoot3" to "true",
            "useTags" to "true",
            "interfaceOnly" to "true"
        )
    )
    // 템플릿 디렉터리 설정
     templateDir.set("$rootDir/contract/template")
}

openApiValidate {
    val contractPath = "$rootDir/contract/$contractFileName"
    inputSpec.set(contractPath)
    recommend.set(true)
}

openApiMeta {
    generatorName.set("meta")
    packageName.set(projectPackageName)
}

// 빌드된 API 파일 이동
tasks.register("moveGeneratedSources") {
    doFirst {
        println("Moving generated sources...")
    }
    doLast {
        listOf("api", "model", "invoker").forEach { packageName ->
            val originDir = file("$buildDir/openapi/src/main/kotlin/$projectPackagePath/$packageName")
            val destinationDir = file("src/main/generated/$projectPackagePath/$packageName")
            originDir.listFiles { file -> file.extension == "kt" }?.forEach { file ->
                val resolvedFile = destinationDir.resolve(file.name)
                if (!resolvedFile.exists() && file.name != "Application.kt") {
                    file.copyTo(destinationDir.resolve(file.name), true)
                }
            }
        }
        println("Generated sources moved.")
    }
    dependsOn("openApiGenerate")
}

tasks.register("cleanGeneratedDirectory") {
    doFirst {
        println("Cleaning generated directory...")
    }
    doLast {
        // $buildDir/generated 디렉터리 삭제
        val generatedDir = file("$buildDir/openapi")
        generatedDir.deleteRecursively()
        println("Generated directory cleaned.")
    }
    dependsOn(tasks.getByName("moveGeneratedSources"))
}

tasks.register("updateOpenApiSpec") {
    doFirst {
        println("Updating OpenAPI spec...")
    }
    doLast {
        println("OpenAPI spec updated.")
    }
    dependsOn(
        tasks.getByName("openApiGenerate"),
        tasks.getByName("moveGeneratedSources"),
        tasks.getByName("cleanGeneratedDirectory")
    )
}

sourceSets {
    main {
        kotlin {
            srcDirs("src/main/generated")
        }
    }
}

tasks.named("clean") {
    val generatedDir = file("src/main/generated")
    generatedDir.deleteRecursively()
    println("Generated directory cleaned.")
}

tasks.named("compileKotlin") {
    dependsOn("updateOpenApiSpec")
}
