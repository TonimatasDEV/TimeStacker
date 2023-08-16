plugins {
    id("net.minecraftforge.gradle") version "[6.0,6.2)"
    id("org.spongepowered.mixin") version "0.7-SNAPSHOT"
    id("idea")
    id("eclipse")
}

val modVersion: String by extra
val minecraftVersion: String by extra
val forgeVersion: String by extra
val forgeVersionRange: String by extra

base {
    archivesName.set("TimeStackerForge")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    withSourcesJar()
}


minecraft {
    mappings("official", minecraftVersion)

    copyIdeResources.set(true)

    runs {
        configureEach {
            workingDirectory(project.file("run"))
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")

            mods {
                create("timestacker") {
                    source(sourceSets.main.get())
                }
            }
        }

        create("client") {
            property("forge.enabledGameTestNamespaces", "timestacker")
        }

        create("server") {
            property("forge.enabledGameTestNamespaces", "timestacker")
            args("--nogui")
        }

        create("data") {
            workingDirectory(project.file("run-data"))
            args("--mod", "timestacker", "--all", "--output", file("src/generated/resources/"), "--existing", file("src/main/resources/"))
        }
    }
}

mixin {
    add(sourceSets.main.get(), "timestacker.refmap.json")
    config("timestacker.mixins.json")
}

sourceSets.main.get().resources { srcDir("src/generated/resources") }

repositories {

}

dependencies {
    minecraft("net.minecraftforge:forge:$minecraftVersion-$forgeVersion")
    annotationProcessor("org.spongepowered:mixin:0.8.5:processor")
}


val replaceProperties = mapOf(
    "minecraftVersion" to minecraftVersion,
    "forgeVersionRange" to forgeVersionRange,
    "modVersion" to modVersion
)

tasks.withType<ProcessResources> {
    inputs.properties(replaceProperties)

    filesMatching("META-INF/mods.toml") {
        expand(replaceProperties)
    }
}

tasks.withType<Jar> {
    manifest {
        attributes(
                "Specification-Title" to "timestacker",
                "Specification-Vendor" to "TonimatasDEV",
                "Specification-Version" to modVersion,
                "Implementation-Title" to "timestacker",
                "Implementation-Version" to modVersion,
                "Implementation-Vendor" to "TonimatasDEV",
        )
    }

    finalizedBy("reobfJar")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
