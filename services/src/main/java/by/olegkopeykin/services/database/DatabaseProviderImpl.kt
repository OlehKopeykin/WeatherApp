package by.olegkopeykin.services.database

import android.content.Context
import androidx.room.Room

class DatabaseProviderImpl(val context: Context) : DatabaseProvider {

	override val db: Database = Room
		.databaseBuilder(context, Database::class.java, "WeatherDatabase")
		.fallbackToDestructiveMigration()
		.build()
}