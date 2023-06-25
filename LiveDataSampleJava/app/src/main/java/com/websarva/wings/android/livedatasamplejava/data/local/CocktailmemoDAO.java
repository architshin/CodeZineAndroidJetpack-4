package com.websarva.wings.android.livedatasamplejava.data.local;

import com.google.common.util.concurrent.ListenableFuture;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CocktailmemoDAO {
	@Query("SELECT * FROM cocktailmemos WHERE id = :id")
	ListenableFuture<Cocktailmemo> findByPK(int id);

	@Insert
	ListenableFuture<Long> insert(Cocktailmemo cocktailmemo);

	@Delete
	ListenableFuture<Integer> delete(Cocktailmemo cocktailmemo);
}
