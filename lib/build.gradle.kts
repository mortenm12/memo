plugins {
    `java-library`
    `maven-publish`
    id("com.github.bjornvester.xjc") version "1.8.2"
}

group = "dk.tinker"
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

xjc {
    xsdDir.set(layout.projectDirectory.dir("src/main/resources/schemas"))
    includes.addAll("*.xsd", "Version1.2/*.xsd")
    excludes.add("MeMo_core.xsd")
    bindingFiles.setFrom(fileTree("src/main/resources/schemas") { include("*.xjb", "Version1.2/*.xjb") })
    outputJavaDir.set(layout.buildDirectory.dir("generated/sources/xjc/java/main"))
}

dependencies {
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.2")
    runtimeOnly("com.sun.xml.bind:jaxb-impl:4.0.5")
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
