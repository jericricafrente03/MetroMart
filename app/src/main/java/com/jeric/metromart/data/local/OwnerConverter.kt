package com.jeric.metromart.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson

class OwnerConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromOwner(owner: OwnerEntity): String {
        return gson.toJson(owner)
    }

    @TypeConverter
    fun toOwner(ownerJson: String): OwnerEntity {
        return gson.fromJson(ownerJson, OwnerEntity::class.java)
    }
}