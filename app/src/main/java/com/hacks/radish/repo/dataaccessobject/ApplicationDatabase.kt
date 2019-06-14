package com.hacks.radish.repo.dataaccessobject

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hacks.radish.repo.dataobject.UserDO

@Database(entities = [/*ImagePairDO::class,*/ UserDO::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase : RoomDatabase() {
    //abstract fun feedDataAccessObject() : FeedDataAccessObject
    //abstract fun userDataAccessObject() : UserDataAccessObject
}