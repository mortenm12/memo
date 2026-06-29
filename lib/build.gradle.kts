plugins {
    `java-library`
    `maven-publish`
}

group = "dk.mortenm"
version = "1.0.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
    withJavadocJar()
    withSourcesJar()
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "memo"
            from(components["java"])

            pom {
                name = "Memo Library"
                description = "A simple memo Java library"
                url = "https://github.com/mortenm12/memo"

                licenses {
                    license {
                        name = "MIT License"
                        url = "https://opensource.org/licenses/MIT"
                    }
                }

                developers {
                    developer {
                        id = "mortenm12"
                        name = "Morten"
                        email = "mortenm14@gmail.com"
                    }
                }

                scm {
                    connection = "scm:git:git://github.com/mortenm12/memo.git"
                    developerConnection = "scm:git:ssh://github.com/mortenm12/memo.git"
                    url = "https://github.com/mortenm12/memo"
                }
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/mortenm12/memo")
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: project.findProperty("gpr.user") as String?
                password = System.getenv("GITHUB_TOKEN") ?: project.findProperty("gpr.key") as String?
            }
        }
    }
}
