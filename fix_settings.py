import re

with open('app/src/main/java/com/example/features/settings/SettingsPage.kt', 'r') as f:
    content = f.read()

content = content.replace("""    ) {
Spacer(modifier = Modifier.height(8.dp))""", """    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(8.dp))""")

with open('app/src/main/java/com/example/features/settings/SettingsPage.kt', 'w') as f:
    f.write(content)
