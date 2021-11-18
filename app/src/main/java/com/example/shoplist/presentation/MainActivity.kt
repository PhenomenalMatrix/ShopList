package com.example.shoplist.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplist.R
import com.example.shoplist.domain.ShopItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter
    private lateinit var btn: FloatingActionButton
    private var txt = ""
    private lateinit var startForResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        btn = findViewById(R.id.addBtn)
        startForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data?.getStringExtra("key")
                txt = intent.toString()
                viewModel.addShopItem(
                    ShopItem(
                        txt,
                        2,
                        true
                    )
                )
            }
        }
        viewModel.shopList.observe(this){
            shopListAdapter.submitList(it)
        }
        setUpRecycler()

    }

    private fun setUpRecycler() {
        val recycler = findViewById<RecyclerView>(R.id.shopListRv)
        with(recycler){
            shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_ENABLE,
                ShopListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_DISABLE,
                ShopListAdapter.MAX_POOL_SIZE
            )
        }

        setUpListeners()
        setUpSwipeListener(recycler)
    }

    private fun setUpSwipeListener(recycler: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }

        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recycler)
    }

    private fun setUpListeners() {
        shopListAdapter.onShopClickListener = {
//            viewModel.changeEnableState(it)
            Log.e("tag", "setUpRecycler: $it")
        }

        btn.setOnClickListener {
//            viewModel.addShopItem(ShopItem("ssss",2,true))
            startForResult.launch(Intent(this,ShopItemActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show()
    }

}