package com.jeric.metromart.data.remote.dto

import com.google.gson.annotations.SerializedName

data class OwnerDto(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val id: Int,
)