import re

with open('app/src/main/java/com/example/pages/prayer/PrayerPage.kt', 'r') as f:
    content = f.read()

pattern = re.compile(
    r'fun PrayerPage\(onOpenDrawer: \(\) -> Unit\) \{\s*'
    r'val context = LocalContext\.current.*?PrayerTimeModel\(\) // Default is N/A for now\s*'
    r'Column\(\s*'
    r'modifier = Modifier\s*'
    r'\.fillMaxSize\(\)\s*'
    r'\.background\(MaterialTheme\.colorScheme\.background\)\s*'
    r'\) \{\s*'
    r'// AppBar.*?'
    r'// Content\s*'
    r'Column\(', re.DOTALL)

replacement = """fun PrayerPage(onOpenDrawer: () -> Unit) {
    val context = LocalContext.current
    val prayerService = remember { PrayerService(context) }
    val locationStatus = prayerService.getLocationStatus()
    val prayerTimes = PrayerTimeModel() // Default is N/A for now
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column("""

content = pattern.sub(replacement, content)

with open('app/src/main/java/com/example/pages/prayer/PrayerPage.kt', 'w') as f:
    f.write(content)
