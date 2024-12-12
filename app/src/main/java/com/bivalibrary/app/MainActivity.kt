package com.bivalibrary.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.bivalibrary.app.navigation.BivaLibraryNavigation
import com.bivalibrary.app.ui.theme.BivaLibraryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BivaLibraryTheme {
                BivaLibraryApp()
            }
        }
    }
}

@Composable
fun BivaLibraryApp(){
    BivaLibraryNavigation()
}