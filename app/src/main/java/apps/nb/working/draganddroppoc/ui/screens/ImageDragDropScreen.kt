package apps.nb.working.draganddroppoc.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import apps.nb.working.draganddroppoc.R
import apps.nb.working.draganddroppoc.ui.components.DragImage
import apps.nb.working.draganddroppoc.ui.components.DropTargetImage
import apps.nb.working.draganddroppoc.ui.theme.Spacing

// mets un exe

@Composable
fun DragAndDropImageScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.title),
                modifier = Modifier.padding(Spacing.spacing16),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
            DragImage(
                url = stringResource(R.string.source_url)
            )
            DropTargetImage(
                url = stringResource(R.string.target_url)
            )
        }
    }
}
