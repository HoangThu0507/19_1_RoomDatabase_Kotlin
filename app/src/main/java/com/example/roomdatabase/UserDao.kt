package com.example.roomdatabase

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import io.reactivex.Observable
import io.reactivex.Single

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
    @Update //(onConflict = REPLACE)
    fun updateUser(user: User?)
    @Delete
    fun deleteUser(user: User?)

    @Query("SELECT * FROM user")
    fun listUser(): Single<MutableList<User?>?>?

}
