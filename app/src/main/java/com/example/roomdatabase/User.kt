package com.example.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User internal constructor(
    @field:ColumnInfo(name = "employ_name") var name: String,
    var address: String,
) {
    @PrimaryKey(autoGenerate = true)
    var employId = 0

}