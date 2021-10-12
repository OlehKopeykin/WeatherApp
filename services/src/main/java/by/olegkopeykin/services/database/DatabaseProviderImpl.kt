package by.olegkopeykin.services.database

import android.content.Context
import androidx.room.Room
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseProviderImpl @Inject constructor(val context: Context) : DatabaseProvider {
    override val db: Database = Room
        .databaseBuilder(context, Database::class.java, "WeatherDatabase")
        .fallbackToDestructiveMigration()
        .build();
}