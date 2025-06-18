package com.jeric.metromart.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.jeric.metromart.domain.model.GithubModel
import com.jeric.metromart.domain.model.OwnerModel
import com.jeric.metromart.ui.home.HomeDetails
import com.jeric.metromart.ui.navigation.NavGraphSetup
import com.jeric.metromart.ui.theme.MetroMartTheme
import com.jeric.metromart.util.MetroMartPreview
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MetroMartTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraphSetup(
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
}

@MetroMartPreview
@Composable
fun MetroMart() {
    HomeDetails(
        uiState = listOf(
            GithubModel(
                id = 1,
                name = "Name1",
                description = "Sample Description 1",
                fullName = "Name1/FullName1",
                owner = OwnerModel(
                    id = 1,
                    avatar_url = "https://example.com/avatar1.png"
                )
            ),
            GithubModel(
                id = 2,
                name = "Name2",
                description = "Sample Description 2",
                fullName = "Name2/FullName2",
                owner = OwnerModel(
                    id = 2,
                    avatar_url = "https://example.com/avatar2.png"
                )
            ),
            GithubModel(
                id = 3,
                name = "Name3",
                description = "Sample Description 3",
                fullName = "Name3/FullName3",
                owner = OwnerModel(
                    id = 3,
                    avatar_url = "https://example.com/avatar3.png"
                )
            ),
            GithubModel(
                id = 4,
                name = "Name4",
                description = "Sample Description 4",
                fullName = "Name4/FullName4",
                owner = OwnerModel(
                    id = 4,
                    avatar_url = "https://example.com/avatar4.png"
                )
            ),
            GithubModel(
                id = 5,
                name = "Name5",
                description = "Sample Description 5",
                fullName = "Name5/FullName5",
                owner = OwnerModel(
                    id = 5,
                    avatar_url = "https://example.com/avatar5.png"
                )
            ),
            GithubModel(
                id = 6,
                name = "Name6",
                description = "Sample Description 6",
                fullName = "Name5/FullName6",
                owner = OwnerModel(
                    id = 5,
                    avatar_url = "https://example.com/avatar5.png"
                )
            ),
        ),
        event = {}
    )
}
