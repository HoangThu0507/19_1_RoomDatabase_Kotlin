package com.example.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User?)

    //    @Insert(onConflict = IGNORE)
    //    Void insertOrReplaceEmploy(User user);
    //    @Update(onConflict = REPLACE)
    //    fun updateEmploy(user: User?)
    //
    //    @Query("DELETE FROM User")
    //    fun deleteAll()
    @Query("SELECT * FROM user")
    fun getListUser(): List<User?>?
}
