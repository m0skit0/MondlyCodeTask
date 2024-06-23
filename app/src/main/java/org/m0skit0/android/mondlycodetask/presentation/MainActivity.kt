package org.m0skit0.android.mondlycodetask.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.m0skit0.android.mondlycodetask.presentation.theme.MondlyCodeTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MondlyCodeTaskTheme {
                MainItemListComposable()
            }
        }
    }
}
