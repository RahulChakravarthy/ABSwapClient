package com.hacks.radish.repo.dataaccessobject.feed

import androidx.room.Dao

@Dao
interface FeedDataAccessObject {
    /*
    @Insert(onConflict = ROLLBACK)
    fun insertSingleFeed(feedDO : ImagePairDO)

    @Insert(onConflict = ROLLBACK)
    fun insertMultipleFeeds(feedDOs : List<ImagePairDO>)

    @Query("SELECT * FROM ImagePairDO")
    fun fetchAllFeeds() : List<ImagePairDO>

    @Query("SELECT * FROM ImagePairDO WHERE id = :feedId")
    fun fetchFeedById (feedId : Long) : ImagePairDO

    @Update(onConflict = ROLLBACK)
    fun updateFeed(feedDO: ImagePairDO)

    @Delete
    fun deleteFeed(feedDO: ImagePairDO)
    */
}