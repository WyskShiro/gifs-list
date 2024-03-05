package will.shiro.giphy.di

import android.app.Application
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ContextModule {
    @Provides
    fun provideResources(application: Application): Resources {
        return application.resources
    }
}