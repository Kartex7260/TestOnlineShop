package kanti.testonlineshop.data.room.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kanti.testonlineshop.data.model.product.datasource.local.FavouriteData
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE id = :productId LIMIT 1")
    suspend fun getProduct(productId: String): ProductEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(products: List<ProductEntity>)

    @Query("DELETE FROM products")
    suspend fun deleteAll()

    @Query("SELECT id, favourite FROM products WHERE favourite = 1")
    suspend fun getFavouriteData(): List<FavouriteData>

    @Update(entity = ProductEntity::class)
    suspend fun setFavouriteData(favouriteData: List<FavouriteData>)

    @Query("UPDATE products SET favourite = :favourite WHERE id = :productId")
    suspend fun setFavourite(productId: String, favourite: Boolean)
}