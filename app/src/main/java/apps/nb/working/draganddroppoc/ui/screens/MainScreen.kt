package apps.nb.working.draganddroppoc.ui.screens

import android.content.ClipData
import android.content.ClipDescription
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import apps.nb.working.draganddroppoc.R
import apps.nb.working.draganddroppoc.ui.components.GenreItem
import apps.nb.working.draganddroppoc.ui.theme.ApplicationTypography
import apps.nb.working.draganddroppoc.ui.theme.Dimension
import apps.nb.working.draganddroppoc.ui.theme.Spacing
import apps.nb.working.draganddroppoc.utils.setHeight

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun MainScreen(
    viewmodel: MainViewModel = hiltViewModel()
) {
    val availableChoices by viewmodel.availableChoices.collectAsStateWithLifecycle()
    val selectedChoices by viewmodel.selectedChoices.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                style = MaterialTheme.typography.displayLarge.copy(color = MaterialTheme.colorScheme.tertiary),
                modifier = Modifier.height(Dimension.dimension80),
                text = stringResource(R.string.main_screen_title)
            )
        })
    }) { innerPadding ->
        val dndSelected = remember {
            object : DragAndDropTarget {
                override fun onDrop(event: DragAndDropEvent): Boolean {
                    val draggedData = event.toAndroidDragEvent().clipData
                    if (draggedData.itemCount > 0) {
                        viewmodel.addSelectedChoice(draggedData.getItemAt(0).text.toString())
                        return true
                    } else {
                        return false
                    }
                }
            }
        }
        val dndAvailable = remember {
            object : DragAndDropTarget {
                override fun onDrop(event: DragAndDropEvent): Boolean {
                    val draggedData = event.toAndroidDragEvent().clipData
                    if (draggedData.itemCount > 0) {
                        viewmodel.addAvailableChoice(draggedData.getItemAt(0).text.toString())
                        return true
                    } else {
                        return false
                    }
                }
            }
        }
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            val selectedHeight = setHeight(selectedChoices.size)
            val availableHeight = setHeight(availableChoices.size)
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title of first Section
                item {
                    Text(
                        modifier = Modifier
                            .padding(
                                top = Spacing.spacing32,
                                bottom = Spacing.spacing8
                            )
                            .align(alignment = Alignment.Center),
                        text = stringResource(R.string.selected_choices),
                        style = ApplicationTypography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                // First Grid of selected choices
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(selectedHeight.dp)
                            .padding(Spacing.spacing16)
                            .background(MaterialTheme.colorScheme.primary)
                            .border(Spacing.spacing2, MaterialTheme.colorScheme.onPrimary)
                            .dragAndDropTarget(
                                shouldStartDragAndDrop = { event ->
                                    event
                                        .mimeTypes()
                                        .contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                                },
                                target = dndSelected
                            )
                    ) {
                        if (selectedChoices.isEmpty()) {
                            Text(
                                text = stringResource(R.string.drag_and_drop_here),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onPrimary,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(Spacing.spacing16)
                                    .align(alignment = Alignment.Center)
                            )
                        } else {
                            LazyVerticalGrid(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(Spacing.spacing16),
                                columns = GridCells.Fixed(3),
                                verticalArrangement = Arrangement.spacedBy(Spacing.spacing16),
                                horizontalArrangement = Arrangement.spacedBy(Spacing.spacing16)
                            ) {
                                items(selectedChoices.size) { item ->
                                    GenreItem(
                                        modifier = Modifier.dragAndDropSource {
                                            detectTapGestures(onLongPress = {
                                                startTransfer(
                                                    DragAndDropTransferData(
                                                        ClipData.newPlainText(
                                                            selectedChoices[item],
                                                            selectedChoices[item]
                                                        )
                                                    )
                                                )
                                            })
                                        },
                                        genre = selectedChoices[item],
                                        hasButton = true,
                                        deleteAction = {
                                            viewmodel.removeSelectedChoice(selectedChoices[item])
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
                // Title of the second section
                item {
                    Text(
                        modifier = Modifier
                            .padding(
                                top = Spacing.spacing32,
                                bottom = Spacing.spacing8
                            )
                            .align(alignment = Alignment.Center),
                        text = stringResource(R.string.available_choices),
                        style = ApplicationTypography.headlineLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        textAlign = TextAlign.Center
                    )
                }
                // Second Grid of available choices
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(availableHeight.dp)
                            .padding(Spacing.spacing16)
                            .background(MaterialTheme.colorScheme.primary)
                            .border(Spacing.spacing2, MaterialTheme.colorScheme.onPrimary)
                            .dragAndDropTarget(
                                shouldStartDragAndDrop = { event ->
                                    event
                                        .mimeTypes()
                                        .contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                                },
                                target = dndAvailable
                            )
                    ) {
                        if (availableChoices.isEmpty()) {
                            Text(
                                modifier = Modifier
                                    .padding(Spacing.spacing16)
                                    .align(alignment = Alignment.Center),
                                text = stringResource(R.string.drag_and_drop_here),
                                style = ApplicationTypography.titleMedium,
                                color = MaterialTheme.colorScheme.secondary,
                                textAlign = TextAlign.Center
                            )
                        } else {
                            LazyVerticalGrid(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(Spacing.spacing16),
                                columns = GridCells.Fixed(3),
                                verticalArrangement = Arrangement.spacedBy(Spacing.spacing16),
                                horizontalArrangement = Arrangement.spacedBy(Spacing.spacing16)
                            ) {
                                items(availableChoices.size) { item ->
                                    GenreItem(
                                        modifier = Modifier.dragAndDropSource {
                                            detectTapGestures(onLongPress = {
                                                startTransfer(
                                                    DragAndDropTransferData(
                                                        ClipData.newPlainText(
                                                            availableChoices[item],
                                                            availableChoices[item]
                                                        )
                                                    )
                                                )
                                            })
                                        },
                                        genre = availableChoices[item]
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
