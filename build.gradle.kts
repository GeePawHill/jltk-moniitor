import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    `java-library`
    `maven-publish`
    signing
    application
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    // Check for updates with ./gradlew dependencyUpdates
    id("com.github.ben-manes.versions") version "0.46.0"
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("org.geepawhill.jltk.Main")
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.9.1")
    implementation("org.geepawhill:jltk:latest.release")
    implementation("commons-cli:commons-cli:1.5.0")
    implementation("org.slf4j:slf4j-nop:2.0.7")
}
// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    withJavadocJar()
    withSourcesJar()
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<ShadowJar> {
    dependsOn("distTar", "distZip")
    minimize()
    manifest {
        attributes["Main-Class"] = "org.geepawhill.jltk.Main"
    }
    archiveClassifier.set("")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifact(tasks["shadowJar"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
            pom {
                name.set("jltk-monitor")
                description.set("The Java Learning ToolKit -- Monitor Program")
                url.set("https://github.com/GeePawHill/jltk-monitor")
                licenses {
                    license {
                        name.set("The MIT License")
                        url.set("https://github.com/GeePawHill/jltk-monitor/blob/main/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("GeePawHill")
                        name.set("GeePaw Hill")
                        email.set("GeePawHill@geepawhill.org")
                    }
                }
                scm {
                    connection.set("git@github.com:GeePawHill/jltk-monitor.git")
                    developerConnection.set("git@github.com:GeePawHill/jltk-monitor.git")
                    url.set("https://github.com/GeePawHill/jltk-monitor.git")
                }
            }

        }
    }
}

nexusPublishing {
    repositories {
        sonatype {  //only for users registered in Sonatype after 24 Feb 2021
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}

signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications["mavenJava"])
}
