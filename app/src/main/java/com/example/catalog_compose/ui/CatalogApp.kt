package com.example.catalog_compose.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.catalog_compose.MainViewModel
import com.example.catalog_compose.ui.screens.MainScreen
import com.example.catalog_compose.ui.screens.image_detail.navigation.imageDetailBottomSheet
import com.example.catalog_compose.ui.screens.image_detail.navigation.imageDetailScreen
import com.example.catalog_compose.ui.screens.image_detail.navigation.navigateToImageDetail
import com.example.catalog_compose.ui.screens.image_detail.navigation.navigateToImageDetailBottomSheet
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator

enum class CatalogAppScreen {
    Main
}

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun CatalogComposeApp(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    bottomSheetNavigator: BottomSheetNavigator = rememberBottomSheetNavigator(),
    navController: NavHostController = rememberNavController(bottomSheetNavigator),
) {
    val images = viewModel.images.collectAsLazyPagingItems()

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
    ) {
        NavHost(
            navController = navController,
            startDestination = CatalogAppScreen.Main.name,
            modifier = modifier,
        ) {
            composable(route = CatalogAppScreen.Main.name) {
                MainScreen(
                    images = images,
                    onImageClick = { navController.navigateToImageDetail(it.id) },
                )
            }
            imageDetailScreen(
                onBackClick = navController::popBackStack,
                onAboutClick = { navController.navigateToImageDetailBottomSheet() },
                onShareClick = { /** TODO: click handle **/ },
            )
            imageDetailBottomSheet(navController)
        }
    }
}