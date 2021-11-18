package com.example.shoplist.presentation

import androidx.lifecycle.ViewModel
import com.example.shoplist.data.ShopListRepositoryImpl
import com.example.shoplist.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl()

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)
//    private val ediShopItemUseCase = EdiShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    private val scope = CoroutineScope(Dispatchers.Default)



    fun addShopItem(shopItem: ShopItem){
        scope.launch {
            addShopItemUseCase.addShopItem(shopItem)
        }

    }

    fun deleteShopItem(shopItem: ShopItem){
        scope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

//    fun changeEnableState(shopItem: ShopItem){
//        scope.launch {
//            val newItem = shopItem.copy(enabled = !shopItem.enabled)
//            ediShopItemUseCase.editShopItem(newItem)
//        }
//    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

}