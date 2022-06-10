package br.com.projeto.sportnews.data.local

import androidx.room.*
import br.com.projeto.sportnews.domain.New
import kotlinx.coroutines.flow.Flow

@Dao
interface NewDAO {

    @Query("SELECT * FROM New")
    fun getNews(): Flow<List<New>>

    @Query("SELECT COUNT(*) FROM New WHERE id = :id")
    suspend fun hasNew(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavorite(new: New)

    @Delete
    suspend fun removeFavorite(new: New)
}