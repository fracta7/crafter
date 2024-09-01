package com.fracta7.crafter.di

import android.app.Application
import androidx.room.Room
import com.fracta7.crafter.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAppDb(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java, "app-database"
        ).build()
    }
}