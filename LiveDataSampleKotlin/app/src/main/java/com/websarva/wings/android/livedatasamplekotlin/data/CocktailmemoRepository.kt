package com.websarva.wings.android.livedatasamplekotlin.data

import android.app.Application
import com.websarva.wings.android.repositorysamplekotlin.data.local.AppDatabase
import com.websarva.wings.android.repositorysamplekotlin.data.local.Cocktailmemo

class CocktailmemoRepository(application: Application) {
	private val _db: AppDatabase

	init {
		_db = AppDatabase.getDatabase(application)
	}

	suspend fun getCocktailmemo(cocktailId: Int): Cocktailmemo? {
		val cocktailmemoDAO = _db.createCocktailmemoDAO()
		return cocktailmemoDAO.findByPK(cocktailId)
	}

	suspend fun deleteInsertCocktailmemo(cocktailmemo: Cocktailmemo): Boolean {
		val cocktailmemoDAO = _db.createCocktailmemoDAO()
		var result = false
		val resultDelete = cocktailmemoDAO.delete(cocktailmemo)
		if(resultDelete >= 0) {
			val resultInsert = cocktailmemoDAO.insert(cocktailmemo)
			if(resultInsert >= 0) {
				result = true
			}
		}
		return result
	}
}
