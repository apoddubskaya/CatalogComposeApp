package com.example.catalog_compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.catalog_compose.MainViewModel
import com.example.catalog_compose.ui.screens.ImageDetailScreen
import com.example.catalog_compose.ui.screens.MainScreen

enum class CatalogAppScreen {
    Main,
    ImageDetail
}

@Composable
fun CatalogComposeApp(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val images = viewModel.images.collectAsLazyPagingItems()

    NavHost(
        navController = navController,
        startDestination = CatalogAppScreen.Main.name,
        modifier = modifier
    ) {
        composable(route = CatalogAppScreen.Main.name) {
            MainScreen(images = images) {
                navController.navigate(CatalogAppScreen.ImageDetail.name)
            }
        }
        composable(route = CatalogAppScreen.ImageDetail.name) {
            ImageDetailScreen()
        }
    }
}