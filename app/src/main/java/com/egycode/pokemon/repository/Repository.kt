package com.egycode.pokemon.repository

import androidx.lifecycle.LiveData
import com.egycode.pokemon.db.PokemonDao
import com.egycode.pokemon.model.Pokemon
import com.egycode.pokemon.model.PokemonResponse
import com.egycode.pokemon.network.PokemonApiService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


class Repository @Inject constructor( val pokemonApiService : PokemonApiService , val pokemonDao: PokemonDao ){

    fun getPekomens () : Observable<PokemonResponse>{
        return pokemonApiService.getPokemons()
    }

    fun insertPokemon (pokemon: Pokemon){
        pokemonDao.insertPokemon(pokemon)
    }

    fun deletePokemon(namePokemon: String){
        pokemonDao.deletePokemon(namePokemon)
    }

    fun getFavPokemons() : LiveData<List<Pokemon>>{
        return pokemonDao.getPokemons()
    }

}