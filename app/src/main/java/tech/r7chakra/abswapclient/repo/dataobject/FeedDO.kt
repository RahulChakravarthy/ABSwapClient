package tech.r7chakra.abswapclient.repo.dataobject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "FeedDO")
data class FeedDO(@PrimaryKey(autoGenerate = true) val id : Long,
                  @ColumnInfo(name = "userDO") val userDO: UserDO,
                  @ColumnInfo(name = "image1Url") val image1Url : String,
                  @ColumnInfo(name = "image2Url") val image2Url : String) : DataObject(), Serializable