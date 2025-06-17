package com.jeric.metromart.di

import com.jeric.metromart.data.local.MetroMartDataBase
import com.jeric.metromart.data.remote.GithubApiService
import com.jeric.metromart.data.repository.ListRepositoryImpl
import com.jeric.metromart.domain.repository.ListRepository
import com.jeric.metromart.domain.use_cases.UseCases
import com.jeric.metromart.domain.use_cases.fetch_data_usecase.FetchDataUseCase
import com.jeric.metromart.domain.use_cases.select_data_usecase.SelectedDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Singleton
    @Provides
    fun provideUseCases(
        repository: ListRepository
    ): UseCases{
        return UseCases(
            fetchDataUseCase = FetchDataUseCase(repository),
            selectedDataUseCase = SelectedDataUseCase(repository)
        )
    }
}