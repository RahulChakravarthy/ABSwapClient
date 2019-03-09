package tech.r7chakra.abswapclient.repo.dataaccessobject.Feed

import androidx.room.*
import tech.r7chakra.abswapclient.repo.dataobject.FeedDO

@Dao
interface FeedDataAccessObject {

    @Insert
    fun insertSingleFeed(feedDO : FeedDO)

    @Insert
    fun insertMultipleFeeds(feedDOs : List<FeedDO>)

    @Query("SELECT * FROM FeedDO")
    fun fetchAllFeeds() : List<FeedDO>

    @Query("SELECT * FROM FeedDO WHERE id = :feedId")
    fun fetchFeedById (feedId : Long) : FeedDO

    @Update
    fun updateFeed(feedDO: FeedDO)

    @Delete
    fun deleteFeed(feedDO: FeedDO)
}