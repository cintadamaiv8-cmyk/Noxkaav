import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    content = f.read()

# Add to banner logic
target_banner = 'currentRoute == "quick_shopping" -> com.example.core.constants.AppAssets.expenseBanner'
replacement_banner = '''currentRoute == "quick_shopping" -> com.example.core.constants.AppAssets.expenseBanner
                    currentRoute == "bulk_shopping" -> com.example.core.constants.AppAssets.expenseBanner'''
content = content.replace(target_banner, replacement_banner)

# Add to nav host
target_nav = '''                        composable("quick_shopping") {
                            com.example.features.expense.QuickShoppingPage(onBack = { navController.popBackStack() })
                        }'''
replacement_nav = '''                        composable("quick_shopping") {
                            com.example.features.expense.QuickShoppingPage(onBack = { navController.popBackStack() })
                        }
                        composable("bulk_shopping") {
                            com.example.features.expense.BulkShoppingPage(onBack = { navController.popBackStack() })
                        }'''
content = content.replace(target_nav, replacement_nav)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(content)
