import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    content = f.read()

# I will replace the BannerCard() call with dynamic pathing
target = r"""                // Persistent Banner
                BannerCard()"""

replacement = """                // Persistent Banner
                val bannerPath = when {
                    currentRoute == com.example.core.routes.AppRoutes.HOME -> com.example.core.constants.AppAssets.homeBanner
                    currentRoute == com.example.core.routes.AppRoutes.PRAYER -> com.example.core.constants.AppAssets.prayerBanner
                    currentRoute == com.example.core.routes.AppRoutes.HISTORY -> com.example.core.constants.AppAssets.historyBanner
                    currentRoute == com.example.core.routes.AppRoutes.BACKUP -> com.example.core.constants.AppAssets.backupBanner
                    currentRoute == com.example.core.routes.AppRoutes.SETTINGS -> com.example.core.constants.AppAssets.settingsBanner
                    currentRoute?.startsWith("placeholder/") == true -> {
                        val titleArg = navBackStackEntry?.arguments?.getString("title") ?: ""
                        when (titleArg) {
                            "Tambah Pengeluaran" -> com.example.core.constants.AppAssets.expenseBanner
                            "Tabungan" -> com.example.core.constants.AppAssets.savingsBanner
                            "Hutang" -> com.example.core.constants.AppAssets.debtBanner
                            "Piutang" -> com.example.core.constants.AppAssets.receivableBanner
                            "YTMP3" -> com.example.core.constants.AppAssets.ytmp3Banner
                            "AI" -> com.example.core.constants.AppAssets.aiBanner
                            "Bot WA" -> com.example.core.constants.AppAssets.botwaBanner
                            "Track COC Mu" -> com.example.core.constants.AppAssets.cocBanner
                            "Browser" -> com.example.core.constants.AppAssets.browserBanner
                            "Maps" -> com.example.core.constants.AppAssets.mapsBanner
                            else -> com.example.core.constants.AppAssets.homeBanner
                        }
                    }
                    else -> com.example.core.constants.AppAssets.homeBanner
                }
                BannerCard(imagePath = bannerPath)"""

content = content.replace(target, replacement)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(content)
