package com.websarva.wings.android.repositorysamplekotlin.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CocktailmemoDAO {
	@Query("SELECT * FROM cocktailmemos WHERE id = :id")
	suspend fun findByPK(id: Int): Cocktailmemo?

	@Insert
	suspend fun insert(cocktailmemo: Cocktailmemo): Long

	@Delete
	suspend fun delete(cocktailmemo: Cocktailmemo): Int
}
