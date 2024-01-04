package com.egycode.pokemon.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.egycode.pokemon.model.Pokemon

@Database(entities = [Pokemon::class], version = 1 , exportSchema = false)
abstract class PokemonDB : RoomDatabase() {

    abstract fun pokemonDao () : PokemonDao

}