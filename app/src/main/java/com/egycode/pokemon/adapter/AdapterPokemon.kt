package com.egycode.pokemon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.egycode.pokemon.R
import com.egycode.pokemon.model.Pokemon

class AdapterPokemon(private val context : Context) : RecyclerView.Adapter<AdapterPokemon.PokemonViewHolder>() {

    var arrayList = arrayListOf<Pokemon>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PokemonViewHolder {
        return PokemonViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.setUi(arrayList.get(position),context)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun getPokemonAt(position: Int) : Pokemon{
        return arrayList[position]
    }

    class PokemonViewHolder(itemView: View) : ViewHolder(itemView) {
        private val pokemonName : TextView = itemView.findViewById(R.id.tv_pokemon)
        private val pokemonImage : ImageView = itemView.findViewById(R.id.img_pokemon)

        fun setUi (pokemon: Pokemon , context: Context){
            pokemonName.text = pokemon.name
            Glide.with(context).load(pokemon.url).into(pokemonImage)

        }

    }
}