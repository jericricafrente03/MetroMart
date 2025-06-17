package com.jeric.metromart.data.remote

import com.jeric.metromart.data.remote.dto.GithubDto
import retrofit2.http.GET

interface GithubApiService {
    @GET("repositories")
    suspend fun getRepositories(): List<GithubDto>
}