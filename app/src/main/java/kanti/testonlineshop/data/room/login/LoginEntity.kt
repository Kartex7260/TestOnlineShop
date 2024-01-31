package kanti.testonlineshop.data.room.login

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "login",
    primaryKeys = ["name", "last_name", "phone"]
)
data class LoginEntity(
    val name: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val phone: String
)
