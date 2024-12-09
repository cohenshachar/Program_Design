tasks.register<Zip>("zip") {
    val projectRootDirectory = project.layout.projectDirectory

    val id1 = project.findProperty("gpr.id1") as String?
        ?: throw IllegalArgumentException("Missing id1 in gradle.properties")
    val id2 = project.findProperty("gpr.id2") as String?
        ?: throw IllegalArgumentException("Missing id2 in gradle.properties")

    archiveFileName = "$id1-$id2-hw1.zip"
    destinationDirectory = projectRootDirectory.dir("archives")
    from("buildSrc") {
        exclude(".gradle", "build")
        into("base/buildSrc")
    }
    from("grades-app") {
        exclude("build")
        into("base/grades-app")
    }
    from("grades-test") {
        exclude("build")
        into("base/grades-test")
    }
    from("library") {
        exclude("build")
        into("base/library")
    }
    from("$projectRootDirectory") {
        include("build.gradle.kts", "gradle.properties", "settings.gradle.kts")
        into("base")
    }
}