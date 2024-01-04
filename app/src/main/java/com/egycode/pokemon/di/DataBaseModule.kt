package com.egycode.pokemon.di

import android.app.Application
import androidx.room.Room
import com.egycode.pokemon.db.PokemonDB
import com.egycode.pokemon.db.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule() {

    @Provides
    @Singleton
    fun provideDB (application: Application) : PokemonDB{
        return Room.databaseBuilder(application,PokemonDB::class.java,"fav_DB" )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(pokemonDB: PokemonDB) : PokemonDao{
        return pokemonDB.pokemonDao()
    }
}