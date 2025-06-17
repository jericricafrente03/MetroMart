package com.jeric.metromart.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeric.metromart.data.remote.dto.OwnerDto
import com.jeric.metromart.util.Constants

@Entity(tableName = Constants.METRO_MART)
data class GithubEntity(
    val description: String,
    @PrimaryKey val id: Int,
    val fullName: String,
    val name: String,
    val owner: OwnerEntity,
)