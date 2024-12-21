package apps.nb.working.draganddroppoc.ui.components

import android.content.ClipDescription
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import apps.nb.working.draganddroppoc.ui.theme.Dimension
import apps.nb.working.draganddroppoc.ui.theme.Spacing
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

// Target Image
@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun DropTargetImage(url: String) {
    val urlState = remember {
        mutableStateOf(url)
    }
    var tintColor by remember {
        mutableStateOf(Color(0xffE5E4E2))
    }
    val dndTarget = remember {
        object : DragAndDropTarget {
            override fun onDrop(event: DragAndDropEvent): Boolean {
                val draggedData = event.toAndroidDragEvent()
                    .clipData.getItemAt(0).text
                urlState.value = draggedData.toString()
                return true
            }

            override fun onEntered(event: DragAndDropEvent) {
                super.onEntered(event)
                tintColor = Color(0xff00ff00)
            }
            override fun onEnded(event: DragAndDropEvent) {
                super.onEntered(event)
                tintColor = Color(0xffE5E4E2)
            }
            override fun onExited(event: DragAndDropEvent) {
                super.onEntered(event)
                tintColor = Color(0xffE5E4E2)
            }
        }
    }
    GlideImage(
        model = urlState.value,
        contentDescription = "Dropped Image",
        colorFilter = ColorFilter.tint(
            color = tintColor,
            blendMode = BlendMode.Modulate
        ),
        modifier = Modifier
            .padding(Spacing.spacing16)
            .size(Dimension.dimension200, Dimension.dimension300)
            .dragAndDropTarget(
                shouldStartDragAndDrop = { event ->
                    event
                        .mimeTypes()
                        .contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                },
                target = dndTarget
            )
    )
}
