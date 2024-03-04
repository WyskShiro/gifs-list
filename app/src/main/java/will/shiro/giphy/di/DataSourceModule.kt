package will.shiro.giphy.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import will.shiro.giphy.data.datasources.IRemoteGifDataSource
import will.shiro.giphy.data.datasources.RemoteGifDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteGifDataSource(
        impl: RemoteGifDataSource
    ): IRemoteGifDataSource
}