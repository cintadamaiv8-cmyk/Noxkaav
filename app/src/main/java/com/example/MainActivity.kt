package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.core.constants.AppAssets
import com.example.core.constants.AppDimensions
import com.example.core.theme.MyApplicationTheme
import com.example.pages.PlaceholderPage
import com.example.pages.home.HomePage
import com.example.pages.prayer.PrayerPage
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.Color

import androidx.compose.animation.core.tween
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.IconButton
import com.example.widgets.BannerCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                NoxKaavApp()
            }
        }
    }
}

@Composable
fun NoxKaavApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    
    val items = listOf(
        NavigationItem("Home", com.example.core.routes.AppRoutes.HOME, Icons.Default.Home),
        NavigationItem("Jadwal Sholat", com.example.core.routes.AppRoutes.PRAYER, Icons.Default.DateRange),
        NavigationItem("Riwayat", com.example.core.routes.AppRoutes.HISTORY, Icons.Default.History),
        NavigationItem("Backup", com.example.core.routes.AppRoutes.BACKUP, Icons.Default.Backup),
        NavigationItem("Pengaturan", com.example.core.routes.AppRoutes.SETTINGS, Icons.Default.Settings)
    )
    
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.background,
                drawerShape = RoundedCornerShape(topEnd = AppDimensions.RadiusDrawer, bottomEnd = AppDimensions.RadiusDrawer),
                modifier = Modifier.width(300.dp)
            ) {
                // Drawer Header
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppDimensions.PaddingCard)
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .border(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f), CircleShape)
                            .padding(2.dp)
                    ) {
                        AsyncImage(
                            model = AppAssets.PROFILE_AVATAR,
                            contentDescription = "Avatar",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "NoxKaav User",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Status • Aktif",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .border(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f), RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "PRO",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Drawer Items
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                
                items.forEach { item ->
                    val isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                    DrawerCapsuleItem(
                        item = item,
                        selected = isSelected,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        val easeInOutCubic = CubicBezierEasing(0.65f, 0.05f, 0.36f, 1f)
        val animDuration = 300

        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
            ) {
                // Persistent AppBar
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val title = when {
                    currentRoute == com.example.core.routes.AppRoutes.HOME -> "Home"
                    currentRoute == com.example.core.routes.AppRoutes.PRAYER -> "Jadwal Sholat"
                    currentRoute == com.example.core.routes.AppRoutes.HISTORY -> "Riwayat"
                    currentRoute == com.example.core.routes.AppRoutes.BACKUP -> "Backup"
                    currentRoute == com.example.core.routes.AppRoutes.SETTINGS -> "Pengaturan"
                    currentRoute == "quick_shopping" -> "Belanja Cepat"
                    currentRoute?.startsWith("placeholder/") == true -> {
                        navBackStackEntry?.arguments?.getString("title") ?: "Menu"
                    }
                    else -> "App"
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { scope.launch { drawerState.open() } }, modifier = Modifier.size(32.dp)) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = MaterialTheme.colorScheme.background,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.weight(1f)
                    )

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f)),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { /* TODO */ }, modifier = Modifier.size(40.dp)) {
                            Icon(
                                imageVector = Icons.Default.NotificationsNone,
                                contentDescription = "Notifications",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }

                // Persistent Banner
                val bannerPath = when {
                    currentRoute == com.example.core.routes.AppRoutes.HOME -> com.example.core.constants.AppAssets.homeBanner
                    currentRoute == com.example.core.routes.AppRoutes.PRAYER -> com.example.core.constants.AppAssets.prayerBanner
                    currentRoute == com.example.core.routes.AppRoutes.HISTORY -> com.example.core.constants.AppAssets.historyBanner
                    currentRoute == com.example.core.routes.AppRoutes.BACKUP -> com.example.core.constants.AppAssets.backupBanner
                    currentRoute == com.example.core.routes.AppRoutes.SETTINGS -> com.example.core.constants.AppAssets.settingsBanner
                    currentRoute == "quick_shopping" -> com.example.core.constants.AppAssets.expenseBanner
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
                BannerCard(imagePath = bannerPath)

                Box(modifier = Modifier.fillMaxSize()) {
                    NavHost(
                        navController = navController,
                        startDestination = com.example.core.routes.AppRoutes.HOME,
                        enterTransition = {
                            fadeIn(animationSpec = tween(animDuration, easing = easeInOutCubic)) +
                            slideInHorizontally(animationSpec = tween(animDuration, easing = easeInOutCubic), initialOffsetX = { 48 }) +
                            scaleIn(initialScale = 0.98f, animationSpec = tween(animDuration, easing = easeInOutCubic))
                        },
                        exitTransition = {
                            fadeOut(animationSpec = tween(animDuration, easing = easeInOutCubic)) +
                            slideOutHorizontally(animationSpec = tween(animDuration, easing = easeInOutCubic), targetOffsetX = { -48 }) +
                            scaleOut(targetScale = 0.98f, animationSpec = tween(animDuration, easing = easeInOutCubic))
                        },
                        popEnterTransition = {
                            fadeIn(animationSpec = tween(animDuration, easing = easeInOutCubic)) +
                            slideInHorizontally(animationSpec = tween(animDuration, easing = easeInOutCubic), initialOffsetX = { -48 }) +
                            scaleIn(initialScale = 0.98f, animationSpec = tween(animDuration, easing = easeInOutCubic))
                        },
                        popExitTransition = {
                            fadeOut(animationSpec = tween(animDuration, easing = easeInOutCubic)) +
                            slideOutHorizontally(animationSpec = tween(animDuration, easing = easeInOutCubic), targetOffsetX = { 48 }) +
                            scaleOut(targetScale = 0.98f, animationSpec = tween(animDuration, easing = easeInOutCubic))
                        }
                    ) {
                        composable(com.example.core.routes.AppRoutes.HOME) {
                            HomePage(
                                onNavigateToPlaceholder = { titleArgument ->
                                    navController.navigate("placeholder/$titleArgument")
                                },
                                onOpenDrawer = {
                                    scope.launch { drawerState.open() }
                                }
                            )
                        }
                        composable(com.example.core.routes.AppRoutes.PRAYER) {
                            PrayerPage(onOpenDrawer = { scope.launch { drawerState.open() } })
                        }
                        composable(com.example.core.routes.AppRoutes.HISTORY) {
                            com.example.features.expense.HistoryPage(onOpenDrawer = { scope.launch { drawerState.open() } })
                        }
                        composable(com.example.core.routes.AppRoutes.BACKUP) {
                            PlaceholderPage(icon = Icons.Default.Backup, title = "Backup", onOpenDrawer = { scope.launch { drawerState.open() } })
                        }
                        composable(com.example.core.routes.AppRoutes.SETTINGS) {
                            com.example.features.settings.SettingsPage(onOpenDrawer = { scope.launch { drawerState.open() } })
                        }
                        composable("quick_shopping") {
                            com.example.features.expense.QuickShoppingPage(onBack = { navController.popBackStack() })
                        }
                        composable(com.example.core.routes.AppRoutes.PLACEHOLDER) { backStackEntry ->
                            val routeTitle = backStackEntry.arguments?.getString("title") ?: "Menu"
                            if (routeTitle == "Tambah Pengeluaran") {
                                com.example.features.expense.ExpenseMenuPage(onNavigateTo = { navController.navigate(it) })
                            } else {
                                PlaceholderPage(icon = Icons.Default.History, title = routeTitle, onOpenDrawer = { scope.launch { drawerState.open() } })
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DrawerCapsuleItem(
    item: NavigationItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent
    val backgroundColor = if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else Color.Transparent
    val contentColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .shadow(if (selected) 8.dp else 0.dp, RoundedCornerShape(24.dp), spotColor = borderColor)
            .background(backgroundColor, RoundedCornerShape(24.dp))
            .border(1.dp, borderColor, RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(item.icon, contentDescription = item.title, tint = contentColor, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = item.title,
            color = contentColor,
            fontSize = 16.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
        )
    }
}

data class NavigationItem(
    val title: String,
    val route: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)
