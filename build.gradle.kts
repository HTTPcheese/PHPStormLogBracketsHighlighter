plugins {
    kotlin("jvm") version "2.1.10"
    id("org.jetbrains.intellij.platform") version "2.10.4"
}

group = "com.httpcheese"
version = "0.1.0"
val phpStormPath = providers.gradleProperty("phpStormPath")

repositories {
    mavenCentral()
    intellijPlatform { defaultRepositories() }
}

dependencies {
    intellijPlatform {
        if (phpStormPath.isPresent) {
            local(phpStormPath.get())
        } else {
            phpstorm("2025.3.2")
        }
    }

    testImplementation(kotlin("test"))
}

intellijPlatform {
    pluginConfiguration {
        id = "com.httpcheese.bracketshighlighter"
        name = "Log Brackets Highlighter"
        version = project.version.toString()

        ideaVersion {
            sinceBuild = "253"
            untilBuild = "253.*"
        }
    }
}

kotlin {
    jvmToolchain(21)
}
