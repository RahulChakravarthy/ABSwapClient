package tech.r7chakra.abswapclient.repo.dataaccessobject.feed

import androidx.room.*
import androidx.room.OnConflictStrategy.ROLLBACK
import tech.r7chakra.abswapclient.repo.dataobject.FeedDO

@Dao
interface FeedDataAccessObject {

    @Insert(onConflict = ROLLBACK)
    fun insertSingleFeed(feedDO : FeedDO)

    @Insert(onConflict = ROLLBACK)
    fun insertMultipleFeeds(feedDOs : List<FeedDO>)

    @Query("SELECT * FROM FeedDO")
    fun fetchAllFeeds() : List<FeedDO>

    @Query("SELECT * FROM FeedDO WHERE id = :feedId")
    fun fetchFeedById (feedId : Long) : FeedDO

    @Update(onConflict = ROLLBACK)
    fun updateFeed(feedDO: FeedDO)

    @Delete
    fun deleteFeed(feedDO: FeedDO)
}