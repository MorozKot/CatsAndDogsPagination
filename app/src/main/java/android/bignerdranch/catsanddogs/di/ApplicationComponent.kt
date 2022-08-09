package android.bignerdranch.catsanddogs.di

import android.app.Application
import android.bignerdranch.catsanddogs.presentation.StartFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(fragment: StartFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ) : ApplicationComponent
    }

    //TODO Проверить работает ли без @BindsInstance application: Application
}