import re

with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

target = """tasks.register<Copy>("copyAppIcon") {
    from("src/main/assets/icons/icon_app.png")
    into("src/main/res/drawable-nodpi/")
    rename("icon_app.png", "ic_app_foreground_generated.png")
    
    doFirst {
        val sourceFile = file("src/main/assets/icons/icon_app.png")
        if (sourceFile.exists()) {
            val bytes = sourceFile.readBytes()
            if (bytes.size < 8) {
                throw GradleException("Icon file is too small to be a valid PNG")
            }
            val isPng = bytes[0] == 0x89.toByte() &&
                        bytes[1] == 0x50.toByte() &&
                        bytes[2] == 0x4E.toByte() &&
                        bytes[3] == 0x47.toByte() &&
                        bytes[4] == 0x0D.toByte() &&
                        bytes[5] == 0x0A.toByte() &&
                        bytes[6] == 0x1A.toByte() &&
                        bytes[7] == 0x0A.toByte()
            if (!isPng) {
                throw GradleException("Source icon is NOT a valid PNG! libpng will fail. Please provide a true PNG file.")
            }
        }
    }
}"""

replacement = """
abstract class CheckPngTask : DefaultTask() {
    @get:org.gradle.api.tasks.InputFile
    abstract val sourceIcon: org.gradle.api.provider.Property<java.io.File>

    @org.gradle.api.tasks.TaskAction
    fun check() {
        val file = sourceIcon.get()
        if (file.exists()) {
            val bytes = file.readBytes()
            if (bytes.size < 8) {
                throw org.gradle.api.GradleException("Icon file is too small to be a valid PNG")
            }
            val isPng = bytes[0] == 0x89.toByte() &&
                        bytes[1] == 0x50.toByte() &&
                        bytes[2] == 0x4E.toByte() &&
                        bytes[3] == 0x47.toByte() &&
                        bytes[4] == 0x0D.toByte() &&
                        bytes[5] == 0x0A.toByte() &&
                        bytes[6] == 0x1A.toByte() &&
                        bytes[7] == 0x0A.toByte()
            if (!isPng) {
                throw org.gradle.api.GradleException("Source icon is NOT a valid PNG! libpng will fail. Please provide a true PNG file.")
            }
        }
    }
}

val checkAppIconPng = tasks.register<CheckPngTask>("checkAppIconPng") {
    sourceIcon.set(file("src/main/assets/icons/icon_app.png"))
}

tasks.register<Copy>("copyAppIcon") {
    dependsOn(checkAppIconPng)
    from("src/main/assets/icons/icon_app.png")
    into("src/main/res/drawable-nodpi/")
    rename("icon_app.png", "ic_app_foreground_generated.png")
}
"""

content = content.replace(target, replacement)

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)
