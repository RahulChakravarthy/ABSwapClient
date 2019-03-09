package tech.r7chakra.abswapclient.repo.dataaccessobject

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tech.r7chakra.abswapclient.repo.dataaccessobject.Feed.FeedDataAccessObject
import tech.r7chakra.abswapclient.repo.dataaccessobject.User.UserDataAccessObject
import tech.r7chakra.abswapclient.repo.dataobject.FeedDO
import tech.r7chakra.abswapclient.repo.dataobject.UserDO

@Database(entities = [UserDO::class, FeedDO::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun feedDataAccess() : FeedDataAccessObject
    abstract fun userDataAccess() : UserDataAccessObject
}