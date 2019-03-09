package tech.r7chakra.abswapclient.repo.dataobject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "UserDO")
data class UserDO(@PrimaryKey(autoGenerate = true) val id : Long,
                  @ColumnInfo(name = "fullName") val fullName : String,
                  @ColumnInfo(name = "instagramHandle") val instagramHandle : String,
                  @ColumnInfo(name = "deviceId") val deviceId : String) : DataObject(), Serializable