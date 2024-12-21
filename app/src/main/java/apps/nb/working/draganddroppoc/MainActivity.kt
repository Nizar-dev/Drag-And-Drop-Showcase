package apps.nb.working.draganddroppoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import apps.nb.working.draganddroppoc.navigation.DnDNavHost
import apps.nb.working.draganddroppoc.ui.theme.DragAndDropPOCTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DragAndDropPOCTheme {
                DnDNavHost()
            }
        }
    }
}
