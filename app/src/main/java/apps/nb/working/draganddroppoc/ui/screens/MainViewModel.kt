package apps.nb.working.draganddroppoc.ui.screens

import androidx.lifecycle.ViewModel
import apps.nb.working.draganddroppoc.ui.components.BottomTab
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor() : ViewModel() {

    // StateFlow to display the selected tab
    private val _selectedTab = MutableStateFlow(BottomTab.HOME)
    val selectedTab: MutableStateFlow<BottomTab> = _selectedTab

    private val _selectedChoices = MutableStateFlow<List<String>>(emptyList())
    val selectedChoices: MutableStateFlow<List<String>> = _selectedChoices

    private val _availableChoices = MutableStateFlow<List<String>>(emptyList())
    val availableChoices: MutableStateFlow<List<String>> = _availableChoices

    // Initialize the ViewModel
    init {
        _selectedTab.value = BottomTab.HOME
        _selectedChoices.value = emptyList()
        _availableChoices.value = listOf(
            "Action",
            "Comedy",
            "Crime",
            "Drama",
            "Family",
            "Fantasy",
            "History",
            "Romance",
            "Thriller"
        )
    }

    // Method to update the selected tab
    internal fun onTabSelected(tab: BottomTab) {
        _selectedTab.value = tab
    }

    // Method to add a choice to the selected list
    internal fun addSelectedChoice(choice: String) {
        if (!_selectedChoices.value.contains(choice)) {
            _selectedChoices.update {
                it + choice
            }
            _availableChoices.update {
                it - choice
            }
        }
    }

    // Method to remove a choice from the selected list
    internal fun removeSelectedChoice(choice: String) {
        _selectedChoices.update {
            it - choice
        }
        _availableChoices.update {
            it + choice
        }
    }

    // Method to update the available choices
    internal fun addAvailableChoice(choice: String) {
        if (!_availableChoices.value.contains(choice)) {
            _availableChoices.update {
                it + choice
            }
            _selectedChoices.update {
                it - choice
            }
        }
    }
}
