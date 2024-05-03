val fabricLoaderVersion: String by extra

dependencies {
    modImplementation("net.fabricmc:fabric-loader:$fabricLoaderVersion")
}

loom {
    @Suppress("UnstableApiUsage")
    mixin.defaultRefmapName.set("timestacker-common.refmap.json")
}

architectury {
    common("fabric", "forge", "neoforge")
}
