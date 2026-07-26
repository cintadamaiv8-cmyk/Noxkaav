import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    content = f.read()

content = content.replace("composable(com.example.core.routes.AppRoutes.HISTORY) {\n                            PlaceholderPage(icon = Icons.Default.History, title = \"Riwayat\", onOpenDrawer = { scope.launch { drawerState.open() } })\n                        }",
"""composable(com.example.core.routes.AppRoutes.HISTORY) {
                            com.example.features.expense.HistoryPage(onOpenDrawer = { scope.launch { drawerState.open() } })
                        }""")

content = content.replace("""composable(com.example.core.routes.AppRoutes.PLACEHOLDER) { backStackEntry ->
                            val routeTitle = backStackEntry.arguments?.getString("title") ?: "Menu"
                            PlaceholderPage(icon = Icons.Default.History, title = routeTitle, onOpenDrawer = { scope.launch { drawerState.open() } })
                        }""",
"""composable("quick_shopping") {
                            com.example.features.expense.QuickShoppingPage(onBack = { navController.popBackStack() })
                        }
                        composable(com.example.core.routes.AppRoutes.PLACEHOLDER) { backStackEntry ->
                            val routeTitle = backStackEntry.arguments?.getString("title") ?: "Menu"
                            if (routeTitle == "Tambah Pengeluaran") {
                                com.example.features.expense.ExpenseMenuPage(onNavigateTo = { navController.navigate(it) })
                            } else {
                                PlaceholderPage(icon = Icons.Default.History, title = routeTitle, onOpenDrawer = { scope.launch { drawerState.open() } })
                            }
                        }""")


with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(content)
