package com.example.halanchallenge

import android.app.Application
import com.example.halanchallenge.di.networkModule
import com.example.halanchallenge.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(networkModule)
            modules(viewModelModule)
        }
    }
}