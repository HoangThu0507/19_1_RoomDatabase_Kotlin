package com.example.roomdatabase

import androidx.room.*

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
    @Delete
    fun deleteUser(user: User?)

    @Query("SELECT * FROM user")
    fun listUser(): List<User?>?
}
