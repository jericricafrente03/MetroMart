package com.jeric.metromart.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jeric.metromart.data.local.dao.GithubDao

@Database(
    entities = [
        GithubEntity::class
    ], version = 1 , exportSchema = false
)
@TypeConverters(OwnerConverter::class)
abstract class MetroMartDataBase : RoomDatabase() {
    abstract fun githubDao(): GithubDao
}