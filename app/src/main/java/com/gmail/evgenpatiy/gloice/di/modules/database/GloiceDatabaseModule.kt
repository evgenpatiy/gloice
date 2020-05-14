package com.gmail.evgenpatiy.gloice.di.modules.database

import android.content.Context
import androidx.room.Room
import com.gmail.evgenpatiy.gloice.api.RestApiInterface
import com.gmail.evgenpatiy.gloice.data.dao.GenreDao
import com.gmail.evgenpatiy.gloice.data.dao.MovieDao
import com.gmail.evgenpatiy.gloice.data.database.DatabaseConstants
import com.gmail.evgenpatiy.gloice.data.database.GloiceDatabase
import com.gmail.evgenpatiy.gloice.di.qualifiers.ApplicationContext
import com.gmail.evgenpatiy.gloice.di.scopes.ApplicationScope
import com.gmail.evgenpatiy.gloice.helpers.validator.GloiceValidator
import com.gmail.evgenpatiy.gloice.repository.BaseRepository
import com.gmail.evgenpatiy.gloice.repository.GloiceRepository
import dagger.Module
import dagger.Provides

@Module
object GloiceDatabaseModule {

    @Provides
    fun provideGloiceDatabase(@ApplicationContext context: Context): GloiceDatabase =
        Room.databaseBuilder(
            context,
            GloiceDatabase::class.java,
            DatabaseConstants.databaseName
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideMovieDao(db: GloiceDatabase): MovieDao = db.movieDao()

    @Provides
    fun provideGenreDao(db: GloiceDatabase): GenreDao = db.genreDao()

    @Provides
    @ApplicationScope
    fun provideGloiceRepository(
        movieDao: MovieDao,
        genreDao: GenreDao,
        api: RestApiInterface,
        networkValidator: GloiceValidator
    ): BaseRepository = GloiceRepository(movieDao, genreDao, api, networkValidator)
}