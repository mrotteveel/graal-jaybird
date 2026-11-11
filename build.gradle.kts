plugins {
    id("java")
    id("org.graalvm.buildtools.native") version "1.1.18"
}

group = "nl.lawinegevaar"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.firebirdsql.jdbc:jaybird:6.0.8-SNAPSHOT")
}

graalvmNative {
    toolchainDetection.set(true)

    binaries {
        named("main") {
            imageName.set("application")
            mainClass.set("nl.lawinegevaar.graal.experiment.Main")
//            resources {
//                autodetect()
//                includedPatterns.addAll(
//                    "^org/firebirdsql/.*\\.properties$"
//                )
//            }
        }
    }
}
