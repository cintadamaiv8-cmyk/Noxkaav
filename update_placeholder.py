import re

with open('app/src/main/java/com/example/pages/PlaceholderPage.kt', 'r') as f:
    content = f.read()

pattern = re.compile(
    r'fun PlaceholderPage\([^)]*\)\s*\{\s*'
    r'Column\(\s*'
    r'modifier = Modifier\s*'
    r'\.fillMaxSize\(\)\s*'
    r'\.background\(MaterialTheme\.colorScheme\.background\)\s*'
    r'\) \{\s*'
    r'// AppBar.*?'
    r'Column\(\s*'
    r'modifier = Modifier\.fillMaxSize\(\)\.padding\(16\.dp\)', re.DOTALL)

replacement = """fun PlaceholderPage(
    icon: ImageVector,
    title: String,
    onOpenDrawer: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)"""

content = pattern.sub(replacement, content)

with open('app/src/main/java/com/example/pages/PlaceholderPage.kt', 'w') as f:
    f.write(content)
