package com.egycode.pokemon.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.egycode.pokemon.model.Pokemon
import com.egycode.pokemon.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers.*
import javax.inject.Inject


@HiltViewModel
class PokemonViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val TAG = "PokemonViewModel"
    val pokemonList: MutableLiveData<ArrayList<Pokemon>> = MutableLiveData()
    lateinit var favList: LiveData<List<Pokemon>>

    @SuppressLint("CheckResult")
    fun getPokemons() {
        repository.getPekomens()
            .subscribeOn(io())
            .map { (_, _, _, list) ->
                list.forEach {
                    val numPoke = it.url.split('/')
                    it.url =
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${numPoke[numPoke.size - 2]}.png"
                }
                list
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { pokemonList.value = it },
                { Log.d(TAG, "getPokemons Error: ${it.message}") }
            )

    }


    fun insertPokemon(pokemon: Pokemon) {
        repository.insertPokemon(pokemon)
    }

    fun deletePokemon(namePokemon: String) {
        repository.deletePokemon(namePokemon)
    }

    fun getFavPokemons() {
        favList = repository.getFavPokemons()
    }

}