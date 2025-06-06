plugins {
    id("java")
    application
    id("checkstyle")
    id("jacoco")
    id("org.sonarqube") version "4.4.1.3373"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("hexlet.code.App")
}

checkstyle {
    toolVersion = "10.12.1"
    configFile = file("${project.rootDir}/config/checkstyle/checkstyle.xml")
    isIgnoreFailures = false
    maxErrors = 0
    maxWarnings = 0
}

jacoco {
    toolVersion = "0.8.11"
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "Parsifal777_java-project-61")
        property("sonar.organization", "parsifal777")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")

    implementation("info.picocli:picocli:4.7.4")
    annotationProcessor("info.picocli:picocli-codegen:4.7.4")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.core:jackson-core:2.15.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.2")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<JavaCompile> {
    options.release.set(17)
    options.compilerArgs.add("-Aproject=${project.group}/${project.name}")
    options.encoding = "UTF-8"
}

tasks.build {
    dependsOn(tasks.check)
}