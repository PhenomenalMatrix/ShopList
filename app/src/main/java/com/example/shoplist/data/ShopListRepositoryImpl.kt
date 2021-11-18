package com.example.shoplist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.shoplist.App
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository
import java.lang.RuntimeException

class ShopListRepositoryImpl : ShopListRepository {

//    private val shopListLD = MutableLiveData<List<ShopItem>>()
//    private val shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id)})

//    private var autoIncrementId = 0
//
//    init {
//        for (i in 0 until 100){
//            val item = ShopItem("Name $i",i,true)
//            addShopItem(item)
//        }
//    }
    private val shopListDao = App.shopDataBase.shopListDao()
    private val mapper = ShopListMapper()



    override suspend fun addShopItem(shopItem: ShopItem) {
//        if (shopItem.id == ShopItem.UNDEFINED_ID){
//            shopItem.id = autoIncrementId++
////            autoIncrementId++
//        }
//        shopList.add(shopItem)
//        updateList()
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopIte(shopItem: ShopItem) {
//        shopList.remove(shopItem)
//        updateList()
        shopListDao.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
//        val oldElement = getShopItem(shopItem.id)
//        shopList.remove(oldElement)
//        addShopItem(shopItem)
//        shopListDao.updateItem(mapper.mapEntityToDbModel(shopItem))
        TODO()
    }

    override suspend fun getShopItem(shopId: Int): ShopItem {
//        return shopList.find {
//            it.id == shopId
//        } ?: throw RuntimeException("Element with id $shopId not found")
//        val dbModel = shopListDao.getShopItem(shopId)
//        return mapper.mapDbModelToEntity(dbModel)
        TODO()
    }

    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(
        shopListDao.getShopList()
    ){
        mapper.mapListDbModelToListEntity(it)
    }

}