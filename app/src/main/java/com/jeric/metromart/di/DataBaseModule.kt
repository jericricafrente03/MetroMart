package com.jeric.metromart.di

import android.content.Context
import androidx.room.Room
import com.jeric.metromart.data.local.MetroMartDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MetroMartDataBase {
        return Room.databaseBuilder(
            context,
            MetroMartDataBase::class.java,
            "metro_db"
        ).build()
    }
}