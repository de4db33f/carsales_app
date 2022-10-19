package com.aplication.carsales.common.utils

import com.aplication.carsales.main_module.model.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(): MainRepository = MainRepository()
}