package com.egycode.pokemon.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_table")
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name: String,
    var url: String
)

