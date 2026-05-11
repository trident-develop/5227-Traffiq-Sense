package com.eyecon.glo

import android.app.Application
import com.eyecon.glo.di.dataStoreModule
import com.eyecon.glo.di.gameModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class SenseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SenseApp)
            modules(
                dataStoreModule,
                gameModule
            )
        }
    }
}