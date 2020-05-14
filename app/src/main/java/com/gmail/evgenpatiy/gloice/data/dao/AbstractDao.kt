package com.gmail.evgenpatiy.gloice.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface AbstractDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(t: List<T>)
}