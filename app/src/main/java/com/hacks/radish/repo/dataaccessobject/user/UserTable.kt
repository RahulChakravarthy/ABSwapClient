package com.hacks.radish.repo.dataaccessobject.user

import android.content.Context
import androidx.room.Room
import com.hacks.radish.repo.dataaccessobject.ApplicationDatabase
import com.hacks.radish.repo.dataaccessobject.BaseTable
import com.hacks.radish.repo.dataaccessobject.DatabaseOperationInterface
import com.hacks.radish.repo.dataobject.UserDO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserTable @Inject constructor(private val context : Context) : BaseTable(context) {

    private val applicationDatabase : ApplicationDatabase
    private val dao : UserDataAccessObject
    private val databaseName = "UserDB"

    init {
        applicationDatabase = Room.databaseBuilder(context, ApplicationDatabase::class.java, databaseName)
            .fallbackToDestructiveMigration()
            .build()
        dao = applicationDatabase.userDataAccessObject()
    }

    fun insertFavouritePlate(userDO: UserDO, operation : DatabaseOperationInterface<Any?>) {
        doDatabaseOperation {
            operation.beforeOperation()
            dao.insertSingleUser(userDO)
            operation.afterOperation(null)
        }
    }
    fun insertFavouritePlates(userDos : List<UserDO>, operation : DatabaseOperationInterface<Any?>) {
        doDatabaseOperation {
            operation.beforeOperation()
            dao.insertMultipleUsers(userDos)
            operation.afterOperation(null)
        }
    }

    fun fetchFavouritePlateById(id : Long, operation : DatabaseOperationInterface<UserDO>) {
        doDatabaseOperation {
            operation.beforeOperation()
            val userDO = dao.fetchUserById(id)
            operation.afterOperation(userDO)
        }
    }

    fun fetchAllFavouritePlates(operation : DatabaseOperationInterface<List<UserDO>>) {
        doDatabaseOperation {
            operation.beforeOperation()
            val userDOs = dao.fetchAllUsers()
            operation.afterOperation(userDOs)
        }
    }

    fun updateFavouritePlate(userDO: UserDO, operation : DatabaseOperationInterface<Any?>) {
        doDatabaseOperation {
            operation.beforeOperation()
            dao.updateUser(userDO)
            operation.afterOperation(null)
        }
    }

    fun deleteFavouritePlate(userDO: UserDO, operation : DatabaseOperationInterface<Any?>) {
        doDatabaseOperation {
            operation.beforeOperation()
            dao.deleteUser(userDO)
            operation.afterOperation(null)
        }
    }


}