package com.eyecon.glo.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.eyecon.glo.data.ScoreBuilder
import com.eyecon.glo.storage.GameRepo
import com.eyecon.glo.storage.GameRepoImpl
import com.eyecon.glo.storage.gameDataStore
import com.eyecon.glo.utils.DeviceSignalsProvider
import com.eyecon.glo.utils.ResolveStartFlowUseCase
import com.eyecon.glo.utils.SavedScoreRouter
import com.eyecon.glo.utils.ScoreParamsCollector
import com.eyecon.glo.viewmodel.StartViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val gameModule = module {

    single<GameRepo> {
        GameRepoImpl(
            dataStore = get()
        )
    }

    single {
        ScoreBuilder()
    }

    single {
        SavedScoreRouter()
    }

    single<GameRepo> {
        GameRepoImpl(get())
    }

    single {
        DeviceSignalsProvider(
            context = androidContext()
        )
    }

    single {
        ScoreParamsCollector(
            signalsProvider = get()
        )
    }

    factory {
        ResolveStartFlowUseCase(
            gameRepo = get(),
            paramsCollector = get(),
            linkBuilder = get(),
            savedScoreRouter = get()
        )
    }

    viewModel {
        StartViewModel(
            resolveStartFlowUseCase = get()
        )
    }
}

val dataStoreModule = module {
    single<DataStore<Preferences>> {
        androidContext().gameDataStore
    }
}