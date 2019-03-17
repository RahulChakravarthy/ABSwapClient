package com.hacks.radish.repo.dataobject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "FeedDO")
data class FeedDO(//@ColumnInfo(name = "userDO") val userDO: UserDO,
                  @ColumnInfo(name = "image1Url")
                  @SerializedName("a_url")
                  @Expose
                  val image1Url : String,
                  @ColumnInfo(name = "image2Url")
                  @SerializedName("a_url")
                  @Expose
                  val image2Url : String) : DataObject(), Serializable {

    @PrimaryKey(autoGenerate = true) var id : Long? = null

    override fun equals(other: Any?): Boolean {
        return if (other is FeedDO) {
            val that : FeedDO = other
            this.id == that.id &&
                //this.userDO == that.userDO &&
                this.image1Url == that.image1Url &&
                this.image2Url == that.image2Url
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        //result = 31 * result + userDO.hashCode()
        result = 31 * result + image1Url.hashCode()
        result = 31 * result + image2Url.hashCode()
        return result
    }
}