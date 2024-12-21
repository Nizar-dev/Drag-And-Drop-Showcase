package apps.nb.working.draganddroppoc.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import apps.nb.working.draganddroppoc.ui.components.BottomTab
import apps.nb.working.draganddroppoc.ui.components.MyBottomBar
import apps.nb.working.draganddroppoc.ui.screens.DragAndDropImageScreen
import apps.nb.working.draganddroppoc.ui.screens.MainScreen
import apps.nb.working.draganddroppoc.ui.screens.MainViewModel

@Composable
fun DnDNavHost(appNavHostController: NavHostController = rememberNavController()) {
    val mainViewModel: MainViewModel = hiltViewModel()
    val currentDestination = appNavHostController.currentBackStackEntryAsState()

    LaunchedEffect(currentDestination.value) {
        when (currentDestination.value?.destination?.route) {
            HomeDestination::class.java.name -> mainViewModel.onTabSelected(BottomTab.HOME)
            ImageDestination::class.java.name -> mainViewModel.onTabSelected(BottomTab.IMAGE)
            else -> {}
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            MyBottomBar(
                { appNavHostController.navigate(HomeDestination) },
                { appNavHostController.navigate(ImageDestination) },
                mainViewModel
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(
                navController = appNavHostController,
                startDestination = HomeDestination
            ) {
                composable<HomeDestination> {
                    MainScreen()
                }
                composable<ImageDestination> {
                    DragAndDropImageScreen()
                }
            }
        }
    }
}
