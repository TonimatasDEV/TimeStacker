plugins {
    id("java")
}

val modVersion: String by extra
val minecraftVersion: String by extra

allprojects {
    version = "$modVersion-$minecraftVersion"
    group = "net.tonimatasdev"
}