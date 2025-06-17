package com.jeric.metromart.data.repository

import androidx.room.withTransaction
import com.jeric.metromart.data.local.MetroMartDataBase
import com.jeric.metromart.data.mapper.toDomain
import com.jeric.metromart.data.mapper.toDomainList
import com.jeric.metromart.data.mapper.toEntityList
import com.jeric.metromart.data.remote.GithubApiService
import com.jeric.metromart.domain.model.GithubModel
import com.jeric.metromart.domain.repository.ListRepository
import com.jeric.metromart.util.NetworkStat
import com.jeric.metromart.util.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ListRepositoryImpl @Inject constructor(
    private val githubApiService: GithubApiService,
    private val database: MetroMartDataBase
) : ListRepository {
    private val dataBase = database.githubDao()

    override fun getRepositories() = networkBoundResource(
        shouldFetch = { NetworkStat.Status },
        query = { dataBase.getAllGithubEntities().map { it.toDomainList() } },
        fetch = { githubApiService.getRepositories() },
        saveFetchResult = { data ->
            database.withTransaction {
                dataBase.clearGithubEntities()
                dataBase.insertGithubEntities(data.toEntityList())
            }
        }
    )

    override suspend fun getSelectedGithubEntity(id: Int): GithubModel =
        withContext(Dispatchers.IO) {
            dataBase.getGithubEntityById(id = id).toDomain()
        }

}
