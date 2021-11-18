package com.example.shoplist.domain

class EdiShopItemUseCase(private val  shopListRepository: ShopListRepository)  {

    suspend fun editShopItem(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem)
    }

}