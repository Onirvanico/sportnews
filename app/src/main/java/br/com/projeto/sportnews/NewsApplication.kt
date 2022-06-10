package br.com.projeto.sportnews

import android.app.Application
import br.com.projeto.sportnews.data.local.NewRoomDatabase

class NewsApplication : Application() {
    val db: NewRoomDatabase by lazy { NewRoomDatabase.getDatabase(this) }
}