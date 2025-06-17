package com.jeric.metromart.domain.use_cases.select_data_usecase

import com.jeric.metromart.domain.model.GithubModel
import com.jeric.metromart.domain.repository.ListRepository
import kotlinx.coroutines.flow.Flow

class SelectedDataUseCase(
    private val repository: ListRepository
) {
    suspend operator fun invoke(id: Int): GithubModel {
        return repository.getSelectedGithubEntity(id = id)
    }
}