package com.hacks.radish.repo.dataaccessobject.feed

import android.content.Context
import com.hacks.radish.repo.dataaccessobject.BaseTable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedTable @Inject constructor(private val context :  Context) : BaseTable(context) {
    /*
    private val applicationDatabase : ApplicationDatabase
    private val dao : FeedDataAccessObject
    private val databaseName = "FeedDB"

    init {
        applicationDatabase = Room.databaseBuilder(context, ApplicationDatabase::class.java, databaseName)
            .fallbackToDestructiveMigration()
            .build()
        dao = applicationDatabase.feedDataAccessObject()
    }

    fun insertFavouritePlate(feedDO: ImagePairDO, operation : DatabaseOperationInterface<Any?>) {
        doDatabaseOperation {
            operation.beforeOperation()
            dao.insertSingleFeed(feedDO)
            operation.afterOperation(null)
        }
    }

    fun insertFavouritePlates(feedDOs : List<ImagePairDO>, operation : DatabaseOperationInterface<Any?>) {
        doDatabaseOperation {
            operation.beforeOperation()
            dao.insertMultipleFeeds(feedDOs)
            operation.afterOperation(null)
        }
    }

    fun fetchFavouritePlateById(id : Long, operation : DatabaseOperationInterface<ImagePairDO>) {
        doDatabaseOperation {
            operation.beforeOperation()
            val feedDO = dao.fetchFeedById(id)
            operation.afterOperation(feedDO)
        }
    }

    fun fetchAllFavouritePlates(operation : DatabaseOperationInterface<List<ImagePairDO>>) {
        doDatabaseOperation {
            operation.beforeOperation()
            val feedDOs = dao.fetchAllFeeds()
            operation.afterOperation(feedDOs)
        }
    }

    fun updateFavouritePlate(feedDO: ImagePairDO, operation : DatabaseOperationInterface<Any?>) {
        doDatabaseOperation {
            operation.beforeOperation()
            dao.updateFeed(feedDO)
            operation.afterOperation(null)
        }
    }

    fun deleteFavouritePlate(feedDO: ImagePairDO, operation : DatabaseOperationInterface<Any?>) {
        doDatabaseOperation {
            operation.beforeOperation()
            dao.deleteFeed(feedDO)
            operation.afterOperation(null)
        }
    }
    */
}