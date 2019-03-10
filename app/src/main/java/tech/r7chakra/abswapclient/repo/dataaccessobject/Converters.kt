package tech.r7chakra.abswapclient.repo.dataaccessobject

import androidx.room.TypeConverter
import com.google.gson.Gson
import tech.r7chakra.abswapclient.repo.dataobject.UserDO

class Converters {

    @TypeConverter
    fun listToJson(value: ArrayList<String>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): ArrayList<String>? {
        val objects = Gson().fromJson(value, Array<String>::class.java) as Array<String>
        return ArrayList<String>().apply { addAll(objects) }
    }

    @TypeConverter
    fun userToJson(value : UserDO?) : String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToUser(value : String) : UserDO {
        return Gson().fromJson(value, UserDO::class.java)
    }

}