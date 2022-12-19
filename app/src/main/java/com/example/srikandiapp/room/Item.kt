package com.example.srikandiapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val harga: String,
    val trakit: String,
    val kilometer: String,
    val transmisi: String,
    val silinder: String,
    val bbm: String,
    val warna: String,
    val kota: String,
    val berlaku: String,
    val urlgambar: String
)