package com.jeric.metromart.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeric.metromart.ui.details.DetailsScreen
import com.jeric.metromart.ui.details.DetailsStateEvent
import com.jeric.metromart.ui.details.DetailsViewModel
import com.jeric.metromart.ui.home.HomeScreen
import com.jeric.metromart.ui.home.HomeViewModel

@Composable
fun NavGraphSetup(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ) {
        composable<Routes.Home> {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = homeViewModel,
                navHostController = navController
            )
        }
        composable<Routes.Details> {
            val viewModel: DetailsViewModel = hiltViewModel()
            val detailsId = it.toRoute<Routes.Details>().id
            LaunchedEffect(key1 = detailsId){
                detailsId.let {
                    viewModel.onEvent(DetailsStateEvent.Event.GotoDetailsEvent(detailsId))
                }
            }
            DetailsScreen(
                viewModel = viewModel,
                navHostController = navController
            )
        }
    }
} 