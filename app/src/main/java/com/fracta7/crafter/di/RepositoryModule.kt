package com.fracta7.crafter.di

import com.fracta7.crafter.data.preferences.UserPreferencesRepositoryImpl
import com.fracta7.crafter.data.repository.AppRepositoryImpl
import com.fracta7.crafter.data.repository.ProjectRepositoryImpl
import com.fracta7.crafter.domain.repository.AppRepository
import com.fracta7.crafter.domain.repository.ProjectRepository
import com.fracta7.crafter.domain.repository.UserPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun repositoryBinder(repositoryImpl: AppRepositoryImpl): AppRepository

    @Binds
    abstract fun projectRepositoryBinder(projectRepositoryImpl: ProjectRepositoryImpl): ProjectRepository

    @Binds
    abstract fun userPreferencesRepositoryBinder(
        userPreferencesRepositoryImpl: UserPreferencesRepositoryImpl
    ): UserPreferencesRepository
}
