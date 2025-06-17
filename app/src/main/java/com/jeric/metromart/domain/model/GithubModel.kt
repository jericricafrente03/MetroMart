package com.jeric.metromart.domain.model

data class GithubModel(
    val description: String,
    val id: Int,
    val name: String,
    val fullName: String,
    val owner: OwnerModel,
)