import re

with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

# Remove the CheckPngTask class
content = re.sub(r'abstract class CheckPngTask : DefaultTask\(\) \{.*?\n\}\n', '', content, flags=re.DOTALL)

# Remove the checkAppIconPng task registration
content = re.sub(r'val checkAppIconPng = tasks\.register<CheckPngTask>\("checkAppIconPng"\) \{.*?\n\}\n', '', content, flags=re.DOTALL)

# Remove the copyAppIcon task
content = re.sub(r'tasks\.register<Copy>\("copyAppIcon"\) \{.*?\n\}\n', '', content, flags=re.DOTALL)

# Remove the preBuild dependency
content = re.sub(r'tasks\.named\("preBuild"\) \{\n    dependsOn\("copyAppIcon"\)\n\}\n', '', content, flags=re.DOTALL)

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)
