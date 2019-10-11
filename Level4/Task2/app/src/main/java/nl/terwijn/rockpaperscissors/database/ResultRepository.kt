package nl.terwijn.shoppinglistkotlin.database

import android.content.Context
import nl.terwijn.shoppinglistkotlin.model.ResultData

class ResultRepository(context: Context) {
    private val resultDao: ResultDao

    init {
        val database =
            ResultRoomDatabase.getDatabase(
                context
            )
        resultDao = database!!.productDao()
    }

    suspend fun getAllResults(): List<ResultData> {
        return resultDao.getAllProducts()
    }

    suspend fun insertResult(result: ResultData) {
        resultDao.insertResult(result)
    }

    suspend fun deleteAllResults() {
        resultDao.deleteAllResults()
    }
}