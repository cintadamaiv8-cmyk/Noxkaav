import re

with open('app/src/main/java/com/example/features/settings/SettingsPage.kt', 'r') as f:
    content = f.read()

# Remove BannerCard import
content = content.replace("import com.example.widgets.BannerCard\n", "")

pattern = re.compile(
    r'fun SettingsPage\(onOpenDrawer: \(\) -> Unit\) \{\s*'
    r'Column\(\s*'
    r'modifier = Modifier\s*'
    r'\.fillMaxSize\(\)\s*'
    r'\.background\(MaterialTheme\.colorScheme\.background\)\s*'
    r'\.padding\(WindowInsets\.statusBars\.asPaddingValues\(\)\)\s*'
    r'\) \{\s*'
    r'// Custom App Bar.*?BannerCard\(\)\s*', re.DOTALL)

replacement = """fun SettingsPage(onOpenDrawer: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
"""

content = pattern.sub(replacement, content)

with open('app/src/main/java/com/example/features/settings/SettingsPage.kt', 'w') as f:
    f.write(content)
