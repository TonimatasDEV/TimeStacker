plugins {
    id("fabric-loom") version "1.3-SNAPSHOT"
}

val modVersion: String by extra
val minecraftVersion: String by extra
val fabricLoaderVersion: String by extra
val fabricApiVersion: String by extra
val yarnMappings: String by extra

base {
    archivesName.set("PotionTimeStackerFabric")
}

repositories {

}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$minecraftVersion" + "$yarnMappings:v2")
    modImplementation("net.fabricmc:fabric-loader:$fabricLoaderVersion")

}

val replaceProperties = mapOf("modVersion" to modVersion, "minecraftVersion" to minecraftVersion)

tasks.withType<ProcessResources> {
    inputs.properties(replaceProperties)

    filesMatching("fabric.mod.json") {
        expand(replaceProperties)
    }
}


tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<Jar> {
    from("LICENSE") {
        rename { "${it}_${project.base.archivesName.get()}"}
    }
}
