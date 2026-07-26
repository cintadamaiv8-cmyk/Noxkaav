import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    content = f.read()

content = content.replace('currentRoute == com.example.core.routes.AppRoutes.SETTINGS -> "Pengaturan"', 
'''currentRoute == com.example.core.routes.AppRoutes.SETTINGS -> "Pengaturan"
                    currentRoute == "quick_shopping" -> "Belanja Cepat"''')

content = content.replace('currentRoute == com.example.core.routes.AppRoutes.SETTINGS -> com.example.core.constants.AppAssets.settingsBanner',
'''currentRoute == com.example.core.routes.AppRoutes.SETTINGS -> com.example.core.constants.AppAssets.settingsBanner
                    currentRoute == "quick_shopping" -> com.example.core.constants.AppAssets.expenseBanner''')

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(content)
