package com.example.srikandiapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDao {

    @Insert
    fun addDataItem(item: Item)

    @Query("SELECT * FROM item ORDER BY nama ASC")
    fun getDataItem() : List<Item>

    @Query("SELECT * FROM item WHERE id=:item_id")
    fun getDataById(item_id: Int): List<Item>
}