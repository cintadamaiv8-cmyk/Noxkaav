import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    content = f.read()

target_banner = 'currentRoute == "bulk_shopping" -> com.example.core.constants.AppAssets.expenseBanner'
replacement_banner = '''currentRoute == "bulk_shopping" -> com.example.core.constants.AppAssets.expenseBanner
                    currentRoute == "money_transaction" -> com.example.core.constants.AppAssets.expenseBanner'''
content = content.replace(target_banner, replacement_banner)

target_nav = '''                        composable("bulk_shopping") {
                            com.example.features.expense.BulkShoppingPage(onBack = { navController.popBackStack() })
                        }'''
replacement_nav = '''                        composable("bulk_shopping") {
                            com.example.features.expense.BulkShoppingPage(onBack = { navController.popBackStack() })
                        }
                        composable("money_transaction") {
                            com.example.features.expense.MoneyTransactionPage(onBack = { navController.popBackStack() })
                        }'''
content = content.replace(target_nav, replacement_nav)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(content)
