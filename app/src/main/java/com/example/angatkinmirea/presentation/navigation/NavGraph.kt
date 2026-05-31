package com.example.angatkinmirea.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.angatkinmirea.presentation.details.DetailsScreen
import com.example.angatkinmirea.presentation.list.LaureatesScreen
import com.example.angatkinmirea.presentation.list.LaureatesViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: LaureatesViewModel
) {

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {

        composable("list") {

            LaureatesScreen(
                state = viewModel.state,

                onRetry = {
                    viewModel.loadData(null, null)
                },

                onFilter = { year, category ->

                    viewModel.loadData(
                        year,
                        category
                    )
                },

                onItemClick = { laureate ->

                    viewModel.selectedLaureate = laureate

                    navController.navigate("details")
                }
            )
        }

        composable("details") {

            viewModel.selectedLaureate?.let {

                DetailsScreen(
                    laureate = it,
                    onBack = {
                        android.util.Log.d("NAV_TEST", "BACK CLICK")
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}