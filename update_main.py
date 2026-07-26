import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    content = f.read()

target = '''                            "Tambah Pengeluaran" -> com.example.core.constants.AppAssets.expenseBanner
                            "Tabungan" -> com.example.core.constants.AppAssets.savingsBanner
                            "Hutang" -> com.example.core.constants.AppAssets.debtBanner
                            "Piutang" -> com.example.core.constants.AppAssets.receivableBanner
                            "YTMP3" -> com.example.core.constants.AppAssets.ytmp3Banner'''

replacement = '''                            "Tambah Pengeluaran" -> com.example.core.constants.AppAssets.expenseBanner
                            "Tabungan" -> com.example.core.constants.AppAssets.savingsBanner
                            "Hutang" -> com.example.core.constants.AppAssets.debtBanner
                            "Piutang" -> com.example.core.constants.AppAssets.receivableBanner
                            "Top Up E-Wallet" -> com.example.core.constants.AppAssets.ewalletBanner
                            "Top Up Game" -> com.example.core.constants.AppAssets.gameTopupBanner
                            "YTMP3" -> com.example.core.constants.AppAssets.ytmp3Banner'''

content = content.replace(target, replacement)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(content)
