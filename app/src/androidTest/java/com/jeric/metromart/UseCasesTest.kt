package com.jeric.metromart

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.jeric.metromart.domain.model.GithubModel
import com.jeric.metromart.domain.model.OwnerModel
import com.jeric.metromart.domain.use_cases.UseCases
import com.jeric.metromart.domain.use_cases.fetch_data_usecase.FetchDataUseCase
import com.jeric.metromart.domain.use_cases.select_data_usecase.SelectedDataUseCase
import com.jeric.metromart.repository.FakeListRepository
import com.jeric.metromart.repository.fakeList
import com.jeric.metromart.ui.details.DetailsItem
import com.jeric.metromart.ui.details.DetailsScreen
import com.jeric.metromart.ui.details.DetailsViewModel
import com.jeric.metromart.ui.home.HomeScreen
import com.jeric.metromart.ui.home.HomeViewModel
import com.jeric.metromart.util.ConstantsTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UseCasesTest {
    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var fetchDataUseCase: FetchDataUseCase
    private lateinit var selectedDataUseCase: SelectedDataUseCase

    private lateinit var getAllUseCases: UseCases

    @Before
    fun setUp() {
        fetchDataUseCase = FetchDataUseCase(repository = FakeListRepository())
        selectedDataUseCase = SelectedDataUseCase(repository = FakeListRepository())
        getAllUseCases = UseCases(
            fetchDataUseCase = fetchDataUseCase,
            selectedDataUseCase = selectedDataUseCase
        )
    }

    @Test
    fun testHomeScreen() {
        val homeViewModel = HomeViewModel(useCases = getAllUseCases)
        composeRule.setContent {
            HomeScreen(
                viewModel = homeViewModel,
                navHostController = rememberNavController()
            )
        }
        with(composeRule) {
            onNodeWithTag(ConstantsTest.GITHUB_COL).assertIsDisplayed()
            onNodeWithTag(ConstantsTest.GITHUB_COL)
                .onChildAt(0).assert(hasTestTag(fakeList().first().id.toString()))
        }
    }

    @Test
    fun detailsScreen() {
        composeRule.setContent {
            DetailsItem(
                githubModel =GithubModel(
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
        with(composeRule) {
            onNodeWithTag(ConstantsTest.NAME).assertIsDisplayed()
            onNodeWithTag(ConstantsTest.ID).assertIsDisplayed()
            onNodeWithTag(ConstantsTest.DESCRIPTION).assertIsDisplayed()
            onNodeWithTag(ConstantsTest.BACK).assertIsDisplayed()
            onNodeWithTag(ConstantsTest.BACK).assertIsDisplayed()

        }
    }


}