package com.fracta7.crafter.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.fracta7.crafter.data.local.db.AppDatabase
import com.fracta7.crafter.data.local.db.MIGRATION_1_3
import com.fracta7.crafter.data.local.db.MIGRATION_2_3
import com.fracta7.crafter.data.local.db.dao.ProjectDao
import com.fracta7.crafter.data.local.registry.RegistryLoader
import com.fracta7.crafter.data.remote.HttpUrlConnectionRegistryClient
import com.fracta7.crafter.data.remote.RegistryHttpClient
import com.fracta7.crafter.data.remote.RegistryUpdateManager
import com.fracta7.crafter.domain.repository.AppRepository
import com.fracta7.crafter.domain.repository.UserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDb(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "app-database"
        )
            .addMigrations(MIGRATION_1_3, MIGRATION_2_3)
            .build()
    }

    @Provides
    fun provideProjectDao(db: AppDatabase): ProjectDao = db.projectDao()

    @Singleton
    @Provides
    fun providePreferencesDataStore(app: Application): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { app.preferencesDataStoreFile("crafter_preferences") }
        )
    }

    @Singleton
    @Provides
    fun provideRegistryHttpClient(): RegistryHttpClient = HttpUrlConnectionRegistryClient()

    @Singleton
    @Provides
    fun provideRegistryUpdateManager(
        app: Application,
        appRepository: AppRepository,
        userPreferencesRepository: UserPreferencesRepository,
        registryLoader: RegistryLoader,
        httpClient: RegistryHttpClient
    ): RegistryUpdateManager {
        return RegistryUpdateManager(
            appRepository = appRepository,
            userPreferencesRepository = userPreferencesRepository,
            registryLoader = registryLoader,
            httpClient = httpClient,
            loadBundledSnapshot = { registryLoader.loadFromAssets(app.assets) },
            cacheDir = File(app.filesDir, "registry_cache"),
            tmpDir = File(app.filesDir, "registry_tmp")
        )
    }
}
