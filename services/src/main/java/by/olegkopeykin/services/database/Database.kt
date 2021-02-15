package by.olegkopeykin.services.database

import androidx.room.Database
import androidx.room.RoomDatabase
import by.olegkopeykin.model.db.CityEntity
import by.olegkopeykin.model.db.WeatherEntity
import by.olegkopeykin.services.database.dao.CityDao
import by.olegkopeykin.services.database.dao.WeatherDao

@Database(
    entities = [
        CityEntity::class,
        WeatherEntity::class
    ],
    version = 3, exportSchema = false
)

abstract class Database : RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun weatherDao(): WeatherDao
}