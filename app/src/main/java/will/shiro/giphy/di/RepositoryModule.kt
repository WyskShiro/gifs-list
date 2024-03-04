package will.shiro.giphy.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import will.shiro.giphy.data.repositories.GifRepository
import will.shiro.giphy.domain.repositories.IGifRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGifRepository(
        impl: GifRepository
    ): IGifRepository
}