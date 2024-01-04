package com.egycode.pokemon


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egycode.pokemon.adapter.AdapterPokemon
import com.egycode.pokemon.databinding.ActivityFavBinding

import com.egycode.pokemon.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavActivity : AppCompatActivity() {

    lateinit var binding: ActivityFavBinding
    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var adapterPokemon: AdapterPokemon


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fav)

        adapterPokemon = AdapterPokemon(applicationContext)
        binding.recycleFave.adapter = adapterPokemon
        binding.recycleFave.layoutManager = LinearLayoutManager(this)
        setupSwipe()

        binding.homeBtn.setOnClickListener {
            finish()
        }
        pokemonViewModel = ViewModelProvider(this)[PokemonViewModel::class.java]
        pokemonViewModel.getFavPokemons()

        pokemonViewModel.favList.observe(this)
        {
            adapterPokemon.arrayList = it as ArrayList
            adapterPokemon.notifyDataSetChanged()
        }

    }

    fun setupSwipe() {
        val callback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder,
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val swipedPokemonPosition: Int = viewHolder.adapterPosition
                    val swipedPokemon = adapterPokemon.getPokemonAt(swipedPokemonPosition).name
                    pokemonViewModel.deletePokemon(swipedPokemon)
                    Toast.makeText(
                        applicationContext,
                        R.string.delete_pokemon_from_dataBase,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        ItemTouchHelper(callback).attachToRecyclerView(binding.recycleFave)
    }
}