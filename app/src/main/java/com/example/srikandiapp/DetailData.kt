package com.example.srikandiapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.example.srikandiapp.room.Constant
import com.example.srikandiapp.room.ItemDB
import kotlinx.android.synthetic.main.activity_detail_data.*
import kotlinx.android.synthetic.main.activity_detail_data.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.itemlist.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailData : AppCompatActivity() {
    private val db by lazy { ItemDB(this) }
    private var itemId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_data)
        setupView()
    }

    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true).apply {
            title = "Detail Data"
        }
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType){
            Constant.TYPE_READ -> {
                getDataById()
            }
        }
    }

    fun getDataById(){
        itemId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val items = db.itemDao().getDataById( itemId )[0]
            tv_i_nama.setText( items.nama )
            tv_i_harga.setText( items.harga )
            tv_i_trakit.setText( items.trakit )
            tv_i_kilometer.setText( items.kilometer )
            tv_i_transmisi.setText( items.transmisi )
            tv_i_silinder.setText( items.silinder )
            tv_i_bbm.setText( items.bbm )
            tv_i_warna.setText( items.warna )
            tv_i_kota.setText( items.kota )
            tv_i_berlaku.setText( items.berlaku )
            Log.d("urlgambar", items.urlgambar)
            Glide.with(i_gambar.rootView)
                .asBitmap()
                .load(items.urlgambar)
                .into(BitmapImageViewTarget(i_gambar))

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}