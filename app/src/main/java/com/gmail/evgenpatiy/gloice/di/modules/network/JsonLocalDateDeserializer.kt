package com.gmail.evgenpatiy.gloice.di.modules.network

import com.gmail.evgenpatiy.gloice.data.database.DatabaseConstants
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.lang.reflect.Type
import java.util.*

class JsonLocalDateDeserializer : JsonDeserializer<LocalDate> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDate = LocalDate.parse(
        json?.asString,
        DateTimeFormatter.ofPattern(DatabaseConstants.datePattern).withLocale(Locale.ENGLISH)
    )
}