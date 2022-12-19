package com.example.srikandiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.srikandiapp.room.Item
import com.example.srikandiapp.room.ItemDB
import kotlinx.android.synthetic.main.activity_add_data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddData : AppCompatActivity() {

    val db by lazy{ItemDB(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)
        setupView()
        setupListener()
    }

    fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun setupListener() {
        btn_tambah.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.itemDao().addDataItem(
                    Item (0,
                        e_nama.text.toString(),
                        e_harga.text.toString(),
                        e_trakit.text.toString(),
                        e_kilometer.text.toString(),
                        e_transmisi.text.toString(),
                        e_silinder.text.toString(),
                        e_bbm.text.toString(),
                        e_warna.text.toString(),
                        e_kota.text.toString(),
                        e_berlaku.text.toString(),
                        e_urlgambar.text.toString()
                    )
                )
                finish()
            }
        }
    }
}