package com.jeric.metromart.domain.use_cases

import com.jeric.metromart.domain.use_cases.fetch_data_usecase.FetchDataUseCase
import com.jeric.metromart.domain.use_cases.select_data_usecase.SelectedDataUseCase

data class UseCases(
    val fetchDataUseCase: FetchDataUseCase,
    val selectedDataUseCase: SelectedDataUseCase
)