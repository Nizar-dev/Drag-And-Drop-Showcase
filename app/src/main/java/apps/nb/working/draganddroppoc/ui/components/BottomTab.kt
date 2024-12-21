package apps.nb.working.draganddroppoc.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import apps.nb.working.draganddroppoc.R

enum class BottomTab(
    val titleResId: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    HOME(titleResId = R.string.Composant, Icons.Filled.Home, Icons.Outlined.Home),
    IMAGE(titleResId = R.string.image, Icons.Filled.AccountBox, Icons.Outlined.AccountBox)
}
