package apps.nb.working.draganddroppoc.ui.components

import android.content.ClipData
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import apps.nb.working.draganddroppoc.ui.theme.Dimension
import apps.nb.working.draganddroppoc.ui.theme.Spacing
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

// Source Drag Image
@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun DragImage(url: String) {
    GlideImage(
        model = url,
        contentDescription = "Dragged Image",
        modifier = Modifier
            .padding(Spacing.spacing16)
            .size(Dimension.dimension200, Dimension.dimension300)
            .dragAndDropSource {
                detectTapGestures(
                    onLongPress = {
                        startTransfer(
                            DragAndDropTransferData(
                                ClipData.newPlainText(
                                    "image uri",
                                    url
                                )
                            )
                        )
                    }
                )
            }
    )
}
