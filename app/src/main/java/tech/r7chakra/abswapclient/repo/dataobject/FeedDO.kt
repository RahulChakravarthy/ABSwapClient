package tech.r7chakra.abswapclient.repo.dataobject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "FeedDO")
data class FeedDO(@PrimaryKey(autoGenerate = true) val id : Long,
                  @ColumnInfo(name = "userDO") val userDO: UserDO,
                  @ColumnInfo(name = "image1Url") val image1Url : String,
                  @ColumnInfo(name = "image2Url") val image2Url : String) : DataObject(), Serializable {

    override fun equals(other: Any?): Boolean {
        return if (other is FeedDO) {
            val that : FeedDO = other
            this.id == that.id &&
                this.userDO == that.userDO &&
                this.image1Url == that.image1Url &&
                this.image2Url == that.image2Url
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + userDO.hashCode()
        result = 31 * result + image1Url.hashCode()
        result = 31 * result + image2Url.hashCode()
        return result
    }
}