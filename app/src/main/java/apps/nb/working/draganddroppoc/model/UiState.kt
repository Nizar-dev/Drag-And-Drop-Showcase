package apps.nb.working.draganddroppoc.model

import apps.nb.working.draganddroppoc.ui.components.BottomTab

data class UiState(
    val selectedTab: BottomTab = BottomTab.HOME,
    val selectedChoices: List<String> = emptyList(),
    val availableChoices: List<String> = listOf(
        "Action",
        "Adventure",
        "Animation",
        "Comedy",
        "Crime",
        "Documentary",
        "Drama",
        "Family",
        "Fantasy",
        "History"
    ),
    val showSnackBar: Boolean = false
)
