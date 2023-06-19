package uz.gita.surviveexam.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.surviveexam.domain.repository.AppRepository
import uz.gita.surviveexam.domain.repository.AppRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAppRepository(impl:AppRepositoryImpl):AppRepository
}