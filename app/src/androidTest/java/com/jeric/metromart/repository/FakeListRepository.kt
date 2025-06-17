package com.jeric.metromart.repository

import com.jeric.metromart.domain.model.GithubModel
import com.jeric.metromart.domain.model.OwnerModel
import com.jeric.metromart.domain.repository.ListRepository
import com.jeric.metromart.util.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class FakeListRepository: ListRepository {
    override fun getRepositories(): Flow<DataState<List<GithubModel>>> = flow {
        emit(DataState.Loading())
        emit(DataState.Success(fakeList()))
    }

    override suspend fun getSelectedGithubEntity(id: Int): GithubModel {
        return fakeList().find { it.id == id } ?: fakeList().first()
    }
}


fun fakeList(): List<GithubModel> {
    return listOf(
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
        )
    )
}
