package com.fracta7.crafter.di

import com.fracta7.crafter.data.repository.AppRepositoryImpl
import com.fracta7.crafter.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun repositoryBinder(repositoryImpl: AppRepositoryImpl): AppRepository
}