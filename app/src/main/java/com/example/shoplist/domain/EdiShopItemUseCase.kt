package com.example.shoplist.domain

class EdiShopItemUseCase(private val  shopListRepository: ShopListRepository)  {

    fun editShopItem(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem)
    }

}