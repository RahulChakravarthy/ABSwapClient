package com.hacks.radish.repo.dataobject

import androidx.room.PrimaryKey
import com.hacks.radish.util.lazyAndroid
import java.io.Serializable


//@Entity(tableName = "FeedDO")
data class FeedDO(val title: String,
                  val creator: String,
                  val tags: List<RenderModelDO.Tag>,
                  val imageA: RenderModelDO.Image,
                  val imageB: RenderModelDO.Image
                  ) : DataObject(), Serializable {

    @PrimaryKey(autoGenerate = true) var id : Long? = null

    val renderModelDO by lazyAndroid {
        RenderModelDO(creator, creator, tags, imageA, imageB)
    }

    //For testing
    companion object {
        fun generateTestFeedDO() : FeedDO {
            return FeedDO("Starry Nights",
                "Edwin \"Yiu Ting\" Lo",
                listOf("Night", "Stars", "Nature").map {
                    RenderModelDO.Tag(it)
                },
                RenderModelDO.Image("https://i.imgur.com/AmWThvw.jpg", 100),
                RenderModelDO.Image("https://i.imgur.com/5on032B.jpg", 200))
        }
    }
}