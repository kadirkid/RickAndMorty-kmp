package dev.kadirkid.rickandmorty.app

import android.app.Application
import dev.kadirkid.rickandmorty.core.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.compose.BuildConfig
import org.koin.core.logger.Level

public class RickAndMortyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            // workaround for https://github.com/InsertKoinIO/koin/issues/1188
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@RickAndMortyApplication)
        }
    }
}