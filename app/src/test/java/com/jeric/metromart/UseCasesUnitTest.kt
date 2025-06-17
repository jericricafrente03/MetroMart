package com.jeric.metromart

import com.jeric.metromart.domain.use_cases.UseCases
import com.jeric.metromart.domain.use_cases.fetch_data_usecase.FetchDataUseCase
import com.jeric.metromart.domain.use_cases.select_data_usecase.SelectedDataUseCase
import com.jeric.metromart.repository.FakeListRepository
import com.jeric.metromart.repository.fakeList
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UseCasesUnitTest {

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
    fun `should return the correct Details when id is provided`() = runTest {
        val expectedId = 1
        val expectedDetails = fakeList().find { it.id == expectedId }
        val result = selectedDataUseCase(expectedId)
        assertEquals(expectedDetails, result)
    }

}