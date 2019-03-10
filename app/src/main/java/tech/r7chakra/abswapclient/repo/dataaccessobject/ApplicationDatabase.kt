package tech.r7chakra.abswapclient.repo.dataaccessobject

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tech.r7chakra.abswapclient.repo.dataaccessobject.feed.FeedDataAccessObject
import tech.r7chakra.abswapclient.repo.dataaccessobject.user.UserDataAccessObject
import tech.r7chakra.abswapclient.repo.dataobject.FeedDO
import tech.r7chakra.abswapclient.repo.dataobject.UserDO

@Database(entities = [FeedDO::class, UserDO::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun feedDataAccessObject() : FeedDataAccessObject
    abstract fun userDataAccessObject() : UserDataAccessObject
}