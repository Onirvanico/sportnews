package br.com.projeto.sportnews.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.projeto.sportnews.domain.New

@Database(entities = [New::class], version = 1, exportSchema = false )
abstract class NewRoomDatabase: RoomDatabase() {

    abstract fun getDAO(): NewDAO
    companion object {

        const val NAME_DB = "news_db"

        @Volatile
        private var INSTANCE: NewRoomDatabase? = null

        fun getDatabase(context: Context): NewRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewRoomDatabase::class.java,
                    NAME_DB
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}