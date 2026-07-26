with open("app/src/main/java/com/example/MainActivity.kt", "r") as f:
    content = f.read()

content = content.replace('"home"', 'com.example.core.routes.AppRoutes.HOME')
content = content.replace('"riwayat"', 'com.example.core.routes.AppRoutes.HISTORY')
content = content.replace('"backup"', 'com.example.core.routes.AppRoutes.BACKUP')
content = content.replace('"pengaturan"', 'com.example.core.routes.AppRoutes.SETTINGS')
content = content.replace('"placeholder/{title}"', 'com.example.core.routes.AppRoutes.PLACEHOLDER')

with open("app/src/main/java/com/example/MainActivity.kt", "w") as f:
    f.write(content)
