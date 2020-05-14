package com.gmail.evgenpatiy.gloice.data.entities.genre

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gmail.evgenpatiy.gloice.api.RestApiConstants
import com.gmail.evgenpatiy.gloice.data.database.DatabaseConstants
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = DatabaseConstants.genresTableName,
    indices = arrayOf(Index(value = [DatabaseConstants.genreIdColumn]))
)
data class Genre(

    @PrimaryKey()
    @ColumnInfo(name = DatabaseConstants.genreIdColumn)
    @SerializedName(RestApiConstants.genreId)
    val genreId: Int,

    @ColumnInfo(name = DatabaseConstants.genreNameColumn)
    @SerializedName(RestApiConstants.genreName)
    val genreName: String
)