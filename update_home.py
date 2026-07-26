import re

with open('app/src/main/java/com/example/pages/home/HomePage.kt', 'r') as f:
    content = f.read()

# Remove BannerCard import
content = content.replace("import com.example.widgets.BannerCard\n", "")

# We want to remove the AppBar and BannerCard()
# Look for the exact block from `Column(` to `BannerCard()`

pattern = re.compile(
    r'fun HomePage\(onNavigateToPlaceholder: \(String\) -> Unit, onOpenDrawer: \(\) -> Unit\) \{\s*'
    r'Column\(\s*'
    r'modifier = Modifier\s*'
    r'\.fillMaxSize\(\)\s*'
    r'\.background\(MaterialTheme\.colorScheme\.background\)\s*'
    r'\.verticalScroll\(rememberScrollState\(\)\)\s*'
    r'\.padding\(WindowInsets\.statusBars\.asPaddingValues\(\)\)\s*'
    r'\) \{\s*'
    r'// Custom App Bar.*?BannerCard\(\)\s*', re.DOTALL)

replacement = """fun HomePage(onNavigateToPlaceholder: (String) -> Unit, onOpenDrawer: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
"""

content = pattern.sub(replacement, content)

with open('app/src/main/java/com/example/pages/home/HomePage.kt', 'w') as f:
    f.write(content)
