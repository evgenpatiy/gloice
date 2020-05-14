package com.gmail.evgenpatiy.gloice.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.gmail.evgenpatiy.gloice.data.database.DatabaseConstants
import com.gmail.evgenpatiy.gloice.data.entities.genre.Genre

@Dao
interface GenreDao : AbstractDao<Genre> {

    @Query(
        "SELECT " + DatabaseConstants.genreNameColumn
                + " FROM " + DatabaseConstants.genresTableName
                + " WHERE " + DatabaseConstants.genreIdColumn + " IN (:genreIds)"
    )
    fun getGenreNamesByIds(genreIds: List<Int>): LiveData<List<String>>
}