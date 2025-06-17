package com.jeric.metromart.data.mapper

import com.jeric.metromart.data.local.GithubEntity
import com.jeric.metromart.data.local.OwnerEntity
import com.jeric.metromart.data.remote.dto.GithubDto
import com.jeric.metromart.domain.model.GithubModel
import com.jeric.metromart.domain.model.OwnerModel

fun GithubDto.toEntity(): GithubEntity{
    return GithubEntity(
        id = this.id,
        name = this.name,
        description = this.description ?: "Not Available",
        fullName = this.fullName,
        owner = OwnerEntity(
            id = this.owner.id,
            avatar_url = this.owner.avatarUrl
        )
    )
}

fun GithubEntity.toDomain(): GithubModel{
    return GithubModel(
        id = this.id,
        name = this.name,
        description = this.description,
        fullName = this.fullName,
        owner = OwnerModel(
            id = this.owner.id,
            avatar_url = this.owner.avatar_url
        )
    )
}

fun List<GithubEntity>.toDomainList(): List<GithubModel> {
    return this.map { it.toDomain() }
}

fun List<GithubDto>.toEntityList(): List<GithubEntity> {
    return this.map { it.toEntity() }
}
