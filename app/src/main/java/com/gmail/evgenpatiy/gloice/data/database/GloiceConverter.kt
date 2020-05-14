package com.gmail.evgenpatiy.gloice.data.database

import androidx.room.TypeConverter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*
import java.util.stream.Collectors

class GloiceConverter {

    companion object {
        val releaseDateFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern(DatabaseConstants.datePattern)

        val displayedDateFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern(DatabaseConstants.movieDetailsDatePattern)
                .withLocale(Locale.US)
    }

    @TypeConverter
    fun releaseDateFromDb(value: String?): LocalDate? {
        return LocalDate.parse(value, releaseDateFormatter)
    }

    @TypeConverter
    fun releaseDateToDb(value: LocalDate?): String? {
        return releaseDateFormatter.format(value)
    }

    @TypeConverter
    fun genresToDb(genres: List<Int>): String {
        return genres
            .stream()
            .map { id -> id.toString() }
            .collect(Collectors.joining(","))
    }

    @TypeConverter
    fun genresFromDb(data: String): List<Int> {
        return data
            .split(",")
            .stream()
            .map { id -> id.toInt() }
            .collect(Collectors.toList())
    }
}