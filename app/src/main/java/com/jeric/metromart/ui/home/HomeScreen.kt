package com.jeric.metromart.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import com.jeric.metromart.domain.model.GithubModel
import com.jeric.metromart.ui.components.ItemDetails
import com.jeric.metromart.ui.components.MainModalDrawerSheet
import com.jeric.metromart.ui.components.MetroMartErrorMessage
import com.jeric.metromart.ui.components.MetroMartLoadingBar
import com.jeric.metromart.ui.navigation.Routes
import com.jeric.metromart.util.ConstantsTest
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, navHostController: NavHostController) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()
    val lifecycleOwner = LocalLifecycleOwner.current
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val items = listOf("Home" to Icons.Outlined.Home, "Favorite" to Icons.Outlined.Favorite)
    val selectedItem by remember { mutableStateOf(items[0]) }

    LaunchedEffect(key1 = viewModel.navigation) {
        viewModel.navigation.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collectLatest { navigation ->
                when (navigation) {
                    is HomeStateEvent.Navigation.GotoDetailsNav ->
                        navHostController.navigate(Routes.Details(navigation.id))

                    HomeStateEvent.Navigation.GotoSearch -> navHostController.navigate(Routes.Search)
                }
            }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MainModalDrawerSheet(
                items = items,
                selectedItem = selectedItem,
                onItemsClick = {
                    scope.launch { drawerState.close() }
                }
            )
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {},
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch { drawerState.open() }
                                },
                            ) {
                                Icon(Icons.Rounded.Menu, contentDescription = null)
                            }
                        },
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background
                        )
                    )
                },
            ) { paddingValues ->
                HomeContent(
                    uiState = uiState,
                    event = viewModel::onEvent,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    )
}

@Composable
fun HomeContent(
    uiState: HomeStateEvent.UiState,
    event: (HomeStateEvent.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        uiState.isLoading -> MetroMartLoadingBar()
        uiState.error != null -> MetroMartErrorMessage(message = uiState.error.toString())
        else -> {
            HomeDetails(
                uiState = uiState.isSuccess,
                event = event,
                modifier = modifier
            )
        }
    }
}

@Composable
fun HomeDetails(
    uiState: List<GithubModel>,
    event: (HomeStateEvent.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        BoxWithConstraints {
            val columns = when(maxWidth) {
                in 0.dp..349.dp -> 1
                in 350.dp..599.dp -> 2
                in 600.dp..899.dp -> 3
                in 900.dp..1199.dp -> 4
                else -> 5
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(columns),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(vertical = 10.dp, horizontal = 10.dp),
                modifier = Modifier
                    .testTag(ConstantsTest.GITHUB_COL)
            ) {
                items(uiState, key = { it.id }) { item ->
                    ItemDetails(
                        githubModel = item,
                        onClick = { event(HomeStateEvent.Event.GotoDetailsEvent(it)) },
                        modifier = Modifier.testTag(item.id.toString())
                    )
                }
            }
        }
    }
}


