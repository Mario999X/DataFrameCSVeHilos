package models

import org.jetbrains.kotlinx.dataframe.annotations.DataSchema

@DataSchema
data class Product(
    val productId: Int,
    val productName: String,
    val supplierId: Int,
    val categoryId: Int,
    val quantityPerUnit: String,
    val unitsPrice: Double,
    val unitsInStock: Int,
    val unitsOnOrder: Int,
    val reorderLevel: Int,
    val discontinued: String
)