package com.jeric.metromart.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jeric.metromart.R
import com.jeric.metromart.domain.model.GithubModel
import com.jeric.metromart.domain.model.OwnerModel
import com.jeric.metromart.ui.components.LocalSafeArea
import com.jeric.metromart.ui.components.MetroMartErrorMessage
import com.jeric.metromart.ui.components.MetroMartLoadingBar
import com.jeric.metromart.ui.components.UiText
import com.jeric.metromart.ui.components.capitalizeWords
import com.jeric.metromart.util.ConstantsTest
import com.jeric.metromart.util.MetroMartPreview
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailsScreen(viewModel: DetailsViewModel, navHostController: NavHostController) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = viewModel.navigation) {
        viewModel.navigation.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collectLatest { navigation ->
                when (navigation) {
                    DetailsStateEvent.Navigation.GotoHomeNav -> navHostController.popBackStack()
                }
            }
    }

    DetailsContent(
        uiState = uiState,
        event = viewModel::onEvent
    )

}

@Composable
fun DetailsContent(
    uiState: DetailsStateEvent.UiState,
    event: (DetailsStateEvent.Event) -> Unit,
) {
    when {
        uiState.isLoading -> MetroMartLoadingBar()
        uiState.error != null -> MetroMartErrorMessage(message = uiState.error.toString())
        else -> {
            uiState.isSuccess?.let {
                DetailsItem(
                    githubModel = it,
                    event = { event(DetailsStateEvent.Event.GotoHomeEvent) }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsItem(
    githubModel: GithubModel,
    event: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = { event() },
                        modifier = Modifier.testTag(ConstantsTest.BACK),
                    ) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
                    }
                },
                title = {
                    Text(
                        text = UiText.DynamicString(githubModel.fullName).asString(),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(ConstantsTest.NAME)
                    )

                },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            Icons.Rounded.Favorite,
                            contentDescription = "Favorite",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = Color.Transparent,
        modifier = Modifier.padding(LocalSafeArea.current),
        content = {
            Box(
                modifier = Modifier
                    .padding(it)
                    .padding(20.dp)
            ) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item("image") {
                        val imageRequest = ImageRequest.Builder(LocalContext.current)
                            .data(githubModel.owner.avatar_url.ifEmpty { R.drawable.ic_splash_inner })
                            .placeholder(drawableResId = R.drawable.ic_splash_inner)
                            .crossfade(true)
                            .build()

                        AsyncImage(
                            model = imageRequest,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .widthIn(max = 500.dp)
                                .fillMaxWidth()
                                .aspectRatio(1.2f)
                                .fillMaxHeight()
                                .clip(MaterialTheme.shapes.medium)
                        )
                    }
                    item("name") {
                        Text(
                            text = UiText.DynamicString(githubModel.name.capitalizeWords())
                                .asString(),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    item("id") {
                        Text(
                            text = UiText.DynamicString(githubModel.id.toString()).asString(),
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = .6f),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag(ConstantsTest.ID)
                        )
                    }
                    item("description") {
                        Text(
                            text = UiText.DynamicString(githubModel.description).asString(),
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = .6f),
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag(ConstantsTest.DESCRIPTION)
                        )
                    }
                }
            }
        }
    )
}

@MetroMartPreview
@Composable
fun DetailsItemPreview() {
    DetailsItem(
        githubModel = GithubModel(
            id = 1,
            name = "Name1",
            description = "Sample Description 1",
            fullName = "Name1/FullName1",
            owner = OwnerModel(
                id = 1,
                avatar_url = "https://example.com/avatar1.png"
            )
        ),
        event = {}
    )
}






