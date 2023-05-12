package com.example.catalog_compose.ui.screens.image_detail.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.catalog_compose.ui.screens.image_detail.ImageDetailBottomSheetRoute
import com.example.catalog_compose.ui.screens.image_detail.ImageDetailRoute
import com.example.catalog_compose.ui.screens.image_detail.ImageDetailViewModel
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

const val imageIdArg = "imageId"
const val imageBlurHashArg = "imageBlurHash"

private const val imageDetailsRoute = "image_detail_route"
private const val imageDetailsRouteWithArgs = "$imageDetailsRoute/{$imageIdArg}/{$imageBlurHashArg}"
private const val imageDetailsBottomSheetRoute = "image_detail_bottom_sheet_route"

fun NavController.navigateToImageDetail(imageId: String, imageBlurHash: String) {
    this.navigate(
        imageDetailsRouteWithArgs
            .replace("{$imageIdArg}", imageId)
            .replace("{$imageBlurHashArg}", imageBlurHash)
    )
}

fun NavController.navigateToImageDetailBottomSheet() {
    this.navigate(imageDetailsBottomSheetRoute)
}

fun NavGraphBuilder.imageDetailScreen(
    onBackClick: () -> Unit,
    onAboutClick: () -> Unit,
    onShareClick: () -> Unit,
) {
    composable(
        route = imageDetailsRouteWithArgs,
        arguments = listOf(
            navArgument(imageIdArg) { type = NavType.StringType },
            navArgument(imageBlurHashArg) { type = NavType.StringType },
        ),
    ) {
        ImageDetailRoute(
            onBackClick = onBackClick,
            onAboutClick = onAboutClick,
            onShareClick = onShareClick,
        )
    }
}

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.imageDetailBottomSheet(navController: NavController) {
    bottomSheet(route = imageDetailsBottomSheetRoute) { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(imageDetailsRouteWithArgs)
        }
        val parentViewModel = hiltViewModel<ImageDetailViewModel>(parentEntry)
        ImageDetailBottomSheetRoute(parentViewModel)
    }
}