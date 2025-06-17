package com.jeric.metromart.domain.use_cases.fetch_data_usecase

import android.util.Log
import com.jeric.metromart.domain.model.GithubModel
import com.jeric.metromart.domain.repository.ListRepository
import com.jeric.metromart.util.DataState
import kotlinx.coroutines.flow.Flow

class FetchDataUseCase(
    private val repository: ListRepository
) {
    operator fun invoke(): Flow<DataState<List<GithubModel>>> {
        return repository.getRepositories()
    }
}