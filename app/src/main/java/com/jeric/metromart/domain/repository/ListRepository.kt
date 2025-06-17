package com.jeric.metromart.domain.repository

import com.jeric.metromart.domain.model.GithubModel
import com.jeric.metromart.util.DataState
import kotlinx.coroutines.flow.Flow

interface ListRepository {
    fun getRepositories(): Flow<DataState<List<GithubModel>>>
    suspend fun getSelectedGithubEntity(id: Int): GithubModel
}
