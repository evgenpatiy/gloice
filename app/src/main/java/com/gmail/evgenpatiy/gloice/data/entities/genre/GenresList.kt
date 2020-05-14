package com.gmail.evgenpatiy.gloice.data.entities.genre

import com.gmail.evgenpatiy.gloice.api.RestApiConstants
import com.google.gson.annotations.SerializedName

data class GenresList(

    @SerializedName(RestApiConstants.genres)
    val genresList: List<Genre>
)