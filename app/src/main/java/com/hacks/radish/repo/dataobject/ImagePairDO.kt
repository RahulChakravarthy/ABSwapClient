package com.hacks.radish.repo.dataobject

import com.google.gson.annotations.SerializedName
import com.hacks.radish.util.lazyAndroid
import java.io.Serializable


//@Entity(tableName = "ImagePairDO")
data class ImagePairDO(@SerializedName("_id") val id: String = "",
                       @SerializedName("created_timestamp") val created_timestamp: String = "",
                       @SerializedName("assets") val images : List<ImageDO> = listOf(),
                       @SerializedName("tags") val tags: List<String> = listOf()
                  ) : DataObject(), Serializable {

    val renderModelDO by lazyAndroid {
        RenderModelDO(id, created_timestamp, tags.map { RenderModelDO.Tag(it) }, images[0], images[1])
    }
}