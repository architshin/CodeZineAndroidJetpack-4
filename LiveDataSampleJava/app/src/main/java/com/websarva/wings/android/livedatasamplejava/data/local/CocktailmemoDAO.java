package com.websarva.wings.android.livedatasamplejava.data.local;

import com.google.common.util.concurrent.ListenableFuture;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CocktailmemoDAO {
	@Query("SELECT * FROM cocktailmemos WHERE id = :id")
	public ListenableFuture<Cocktailmemo> findByPK(int id);

	@Insert
	public ListenableFuture<Long> insert(Cocktailmemo cocktailmemo);

	@Delete
	public ListenableFuture<Integer> delete(Cocktailmemo cocktailmemo);
}
