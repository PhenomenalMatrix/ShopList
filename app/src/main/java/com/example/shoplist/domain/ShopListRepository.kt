package com.example.shoplist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopIte(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(shopId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>


}