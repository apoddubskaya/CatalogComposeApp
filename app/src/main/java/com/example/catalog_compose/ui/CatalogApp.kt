package com.example.catalog_compose.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.catalog_compose.MainViewModel
import com.example.catalog_compose.R
import com.example.catalog_compose.ui.screens.ImageDetailBottomSheet
import com.example.catalog_compose.ui.screens.ImageDetailScreen
import com.example.catalog_compose.ui.screens.MainScreen
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator

enum class CatalogAppScreen {
    Main,
    ImageDetail,
    ImageDetailBottomSheet,
}

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun CatalogComposeApp(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
    bottomSheetNavigator: BottomSheetNavigator = rememberBottomSheetNavigator(),
    navController: NavHostController = rememberNavController(bottomSheetNavigator),
) {
    val images = viewModel.images.collectAsLazyPagingItems()
    val selectedImage by viewModel.selectedImage.collectAsState()
    val selectedImageDetails by viewModel.selectedImageDetails.collectAsState()

    ModalBottomSheetLayout(bottomSheetNavigator) {
        NavHost(
            navController = navController,
            startDestination = CatalogAppScreen.Main.name,
            modifier = modifier
        ) {
            composable(route = CatalogAppScreen.Main.name) {
                MainScreen(
                    images = images,
                    onImageClick = {
                        viewModel.selectImage(it)
                        navController.navigate(CatalogAppScreen.ImageDetail.name)
                    }
                )
            }
            composable(route = CatalogAppScreen.ImageDetail.name) {
                val context = LocalContext.current
                val imageDetailsNotAvailableToastText =
                    stringResource(id = R.string.image_detail_screen_details_not_available)
                ImageDetailScreen(
                    image = selectedImage,
                    onBackClick = { navController.popBackStack() },
                    onAboutClick = {
                        if (selectedImageDetails != null) {
                            navController.navigate(CatalogAppScreen.ImageDetailBottomSheet.name)
                        } else {
                            Toast.makeText(context, imageDetailsNotAvailableToastText, Toast.LENGTH_LONG).show()
                        }
                    },
                    onShareClick = { /** TODO: click handle **/ },
                )
            }
            bottomSheet(route = CatalogAppScreen.ImageDetailBottomSheet.name) {
                ImageDetailBottomSheet(image = selectedImage, imageDetails = selectedImageDetails)
            }
        }
    }
}