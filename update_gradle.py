import re

with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

task = """
tasks.register<Copy>("copyAppIcon") {
    from("src/main/assets/icons/icon_app.png")
    into("src/main/res/drawable-nodpi/")
    rename("icon_app.png", "ic_app_foreground_generated.png")
}
tasks.named("preBuild") {
    dependsOn("copyAppIcon")
}
"""

content = content + "\n" + task

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)
