package com.example.tutorialhachinet

import android.app.Application
import com.example.tutorialhachinet.di.networkModule
import com.example.tutorialhachinet.di.repositoryModule
import com.example.tutorialhachinet.di.viewModelModule
import com.example.tutorialhachinet.network.GlobalResponseOperator
import com.skydoves.sandwich.SandwichInitializer
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppController)
            modules(networkModule)
//            modules(persistenceModule)
            modules(repositoryModule)
            modules(viewModelModule)
        }

        // initialize global sandwich operator
        SandwichInitializer.sandwichOperator = GlobalResponseOperator<Any>(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
