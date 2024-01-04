package com.egycode.pokemon.model

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: String,
    var results: ArrayList<Pokemon>
)
