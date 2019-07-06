package com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.diannaliset.vehicles.utils.SingletonHolder
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration
import androidx.annotation.VisibleForTesting



@Database(entities = [VehicleDataClass::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun vehicleDao(): VehicleDao




    companion object : SingletonHolder<AppDatabase, Context>({
        /**
         * Migrate from:
         * version 1 - using the SQLiteDatabase API
         * to
         * version 2 - using Room
         */
        @VisibleForTesting
        val migration: Migration = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                // Room uses an own database hash to uniquely identify the database
                // Since version 1 does not use Room, it doesn't have the database hash associated.
                // By implementing a Migration class, we're telling Room that it should use the data
                // from version 1 to version 2.
                // If no migration is provided, then the tables will be dropped and recreated.
                // Since we didn't alter the table, there's nothing else to do here.
            }
        }
        Room.databaseBuilder(it.applicationContext,
                AppDatabase::class.java, "vehicles.db")
                .addMigrations(migration)
                .build()

    })


}