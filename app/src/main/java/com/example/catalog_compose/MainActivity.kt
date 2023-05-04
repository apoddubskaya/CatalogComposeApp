package com.example.catalog_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.catalog_compose.ui.CatalogComposeApp
import com.example.catalog_compose.ui.theme.CatalogComposeTheme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi

@OptIn(ExperimentalMaterialNavigationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatalogComposeTheme {
                CatalogComposeApp()
            }
        }
    }
}