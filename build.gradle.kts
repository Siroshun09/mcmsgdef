plugins {
    `java-library`
    alias(libs.plugins.mavenPublication)
    alias(libs.plugins.mavenCentralPortal)
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnlyApi(libs.annotations)
    compileOnlyApi(libs.adventure)
    compileOnlyApi(libs.adventure.text.minimessage)

    testImplementation(libs.adventure)
    testImplementation(libs.adventure.text.minimessage)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks {
    compileJava {
        sourceCompatibility = JavaVersion.VERSION_21.majorVersion
        targetCompatibility = JavaVersion.VERSION_21.majorVersion
    }

    test {
        useJUnitPlatform()
    }
}

mavenPublication {
    localRepository(mavenCentralPortal.stagingDirectory)
    description("A library to define and load messages for Adventure's translation system.")
    apacheLicense()
    developer("Siroshun09")
    github("Siroshun09/mcmsgdef")
}
