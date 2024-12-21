package apps.nb.working.draganddroppoc.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import apps.nb.working.draganddroppoc.ui.screens.MainViewModel
import apps.nb.working.draganddroppoc.ui.theme.Dimension
import apps.nb.working.draganddroppoc.ui.theme.Spacing

@Composable
internal fun MyBottomBar(
    onNavigateToHome: () -> Unit,
    onNavigateToImage: () -> Unit,
    viewmodel: MainViewModel
) {
    val selectedTab = viewmodel.selectedTab.collectAsStateWithLifecycle()
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(MaterialTheme.colorScheme.primary, Color.Black)
    )
    Box {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimension.dimension60)

        ) {
            drawRect(gradientBrush)
        }
        NavigationBar(
            modifier = Modifier.fillMaxWidth().height(Dimension.dimension60),
            tonalElevation = Spacing.spacing6,
            containerColor = Color.Transparent,
            contentColor = Color.Transparent
        ) {
            BottomTab.entries.forEach { tab ->
                NavigationBarItem(
                    selected = selectedTab.value == tab,
                    onClick = {
                        when (tab) {
                            BottomTab.HOME -> onNavigateToHome()
                            BottomTab.IMAGE -> onNavigateToImage()
                        }
                        viewmodel.onTabSelected(tab)
                    },
                    icon = {
                        Icon(
                            imageVector = if (selectedTab.value == tab) {
                                tab.selectedIcon
                            } else {
                                tab.unselectedIcon
                            },
                            contentDescription = tab.name,
                            tint = if (selectedTab.value == tab) {
                                Color.White
                            } else {
                                Color.White.copy(alpha = 0.5f)
                            }
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.White.copy(alpha = 0.5f),
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}
