package com.egycode.pokemon.network

import com.egycode.pokemon.model.PokemonResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface PokemonApiService {

    @GET("pokemon")
    fun  getPokemons (): Observable<PokemonResponse>

}