package com.egycode.pokemon

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egycode.pokemon.adapter.AdapterPokemon
import com.egycode.pokemon.databinding.ActivityMainBinding
import com.egycode.pokemon.model.Pokemon
import com.egycode.pokemon.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var adapterPokemon: AdapterPokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        adapterPokemon = AdapterPokemon(applicationContext)
        binding.recycleView.adapter = adapterPokemon
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        setupSwipe()

        binding.favBtn.setOnClickListener {
            startActivity(Intent(this, FavActivity::class.java))
        }

        pokemonViewModel = ViewModelProvider(this)[PokemonViewModel::class.java]
        pokemonViewModel.getPokemons()

        pokemonViewModel.pokemonList.observe(
            this
        ) {
            adapterPokemon.arrayList = it
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
                    return  false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val swipedPokemonPosition: Int = viewHolder.adapterPosition
                    val swipedPokemon: Pokemon = adapterPokemon.getPokemonAt(swipedPokemonPosition)
                    adapterPokemon.notifyDataSetChanged()
                    pokemonViewModel.insertPokemon(swipedPokemon)
                    Toast.makeText(
                        applicationContext,
                        R.string.add_pokemon_to_database,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

         ItemTouchHelper(callback).attachToRecyclerView(binding.recycleView)
    }
}