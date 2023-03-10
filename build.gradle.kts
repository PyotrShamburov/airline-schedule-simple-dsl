plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "1.7.20"
  id("org.jetbrains.intellij") version "1.10.1"
}

group = "com.example.dsl"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
  version.set("2022.1.4")
  type.set("IC") // Target IDE Platform

  plugins.set(listOf(/* Plugin Dependencies */))
}

tasks {
  // Set the JVM compatibility versions
  withType<JavaCompile> {
    sourceCompatibility = "11"
    targetCompatibility = "11"
  }
  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
  }

  patchPluginXml {
    sinceBuild.set("221")
    untilBuild.set("231.*")
  }

  test {
    useJUnitPlatform()
  }

  signPlugin {
    certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
    privateKey.set(System.getenv("PRIVATE_KEY"))
    password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
  }

  publishPlugin {
    token.set(System.getenv("PUBLISH_TOKEN"))
  }
}

dependencies {
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.6.1")
  testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.6.1")
}

