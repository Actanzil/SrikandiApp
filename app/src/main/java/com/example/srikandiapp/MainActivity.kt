package com.example.srikandiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.srikandiapp.room.Constant
import com.example.srikandiapp.room.Item
import com.example.srikandiapp.room.ItemDB
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val db by lazy { ItemDB(this) }
    lateinit var itemlistAdapter: ItemlistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
            itemlistAdapter.setData(db.itemDao().getDataItem())
            withContext(Dispatchers.Main) {
                itemlistAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun setupView (){
        supportActionBar?.hide()
    }

    fun intentDetail(itemId: Int, intentType: Int){
        startActivity(
            Intent(applicationContext, DetailData::class.java)
                .putExtra("intent_id", itemId)
                .putExtra("intent_type", intentType)
        )
    }
    private fun setupRecyclerView () {

        itemlistAdapter = ItemlistAdapter(
            arrayListOf(),
            object : ItemlistAdapter.OnAdapterListener {
                override fun onClick(item: Item) {
                    intentDetail(item.id, Constant.TYPE_READ)
                }
            })

        rv_item.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = itemlistAdapter
        }

    }
}