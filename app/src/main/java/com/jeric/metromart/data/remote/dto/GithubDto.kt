package com.jeric.metromart.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GithubDto(
    val description: String?,
    val id: Int,
    @SerializedName("full_name")
    val fullName: String,
    val name: String,
    val owner: OwnerDto,
)