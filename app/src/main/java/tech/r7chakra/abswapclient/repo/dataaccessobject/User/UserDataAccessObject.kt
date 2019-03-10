package tech.r7chakra.abswapclient.repo.dataaccessobject.user

import androidx.room.*
import tech.r7chakra.abswapclient.repo.dataobject.UserDO

@Dao
interface UserDataAccessObject {

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    fun insertSingleUser(userDO: UserDO)

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    fun insertMultipleUsers(userDOs : List<UserDO>)

    @Query("SELECT * FROM UserDO")
    fun fetchAllUsers() : List<UserDO>

    @Query("SELECT * FROM UserDO WHERE id = :userId")
    fun fetchUserById(userId : Long) : UserDO

    @Update(onConflict = OnConflictStrategy.ROLLBACK)
    fun updateUser(userDO: UserDO)

    @Delete
    fun deleteUser(userDO: UserDO)
}