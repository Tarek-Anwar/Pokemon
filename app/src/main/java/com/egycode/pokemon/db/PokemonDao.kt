package com.egycode.pokemon.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.egycode.pokemon.model.Pokemon

@Dao
interface PokemonDao {

    @Insert
    fun insertPokemon(pokemon: Pokemon)

    @Query("delete from fav_table where name=:pokemonName")
    fun deletePokemon(pokemonName:String)

    @Query("select * from fav_table")
    fun getPokemons () : LiveData<List<Pokemon>>


}