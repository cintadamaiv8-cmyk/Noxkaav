import re

with open('app/src/main/java/com/example/pages/PlaceholderPage.kt', 'r') as f:
    content = f.read()

pattern = re.compile(
    r'// AppBar\s*'
    r'Row\(\s*'
    r'modifier = Modifier\s*'
    r'\.fillMaxWidth\(\)\s*'
    r'\.padding\(horizontal = 16\.dp, vertical = 16\.dp\),\s*'
    r'verticalAlignment = Alignment\.CenterVertically\s*'
    r'\) \{\s*'
    r'Box\(\s*'
    r'modifier = Modifier\s*'
    r'\.clip\(RoundedCornerShape\(8\.dp\)\)\s*'
    r'\.background\(MaterialTheme\.colorScheme\.primary\),\s*'
    r'contentAlignment = Alignment\.Center\s*'
    r'\) \{\s*'
    r'IconButton\(onClick = \{ onOpenDrawer\(\) \}, modifier = Modifier\.size\(32\.dp\)\) \{\s*'
    r'Icon\(\s*'
    r'imageVector = Icons\.Default\.Menu,\s*'
    r'contentDescription = "Menu",\s*'
    r'tint = MaterialTheme\.colorScheme\.background,\s*'
    r'modifier = Modifier\.size\(20\.dp\)\s*'
    r'\)\s*'
    r'\}\s*'
    r'\}\s*'
    r'Spacer\(modifier = Modifier\.width\(16\.dp\)\)\s*'
    r'Text\(\s*'
    r'text = title,\s*'
    r'fontSize = 20\.sp,\s*'
    r'fontWeight = FontWeight\.Bold,\s*'
    r'color = MaterialTheme\.colorScheme\.onBackground,\s*'
    r'modifier = Modifier\.weight\(1f\)\s*'
    r'\)\s*'
    r'\}\s*'
)

content = pattern.sub('', content)

with open('app/src/main/java/com/example/pages/PlaceholderPage.kt', 'w') as f:
    f.write(content)
