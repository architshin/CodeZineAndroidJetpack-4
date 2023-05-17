package com.websarva.wings.android.livedatasamplejava.data;

import android.app.Application;

import com.google.common.util.concurrent.ListenableFuture;
import com.websarva.wings.android.livedatasamplejava.data.local.AppDatabase;
import com.websarva.wings.android.livedatasamplejava.data.local.Cocktailmemo;
import com.websarva.wings.android.livedatasamplejava.data.local.CocktailmemoDAO;

import java.util.concurrent.ExecutionException;

public class CocktailmemoRepository {
	private AppDatabase _db;
	public CocktailmemoRepository(Application application) {
		_db = AppDatabase.getDatabase(application);
	}

	public Cocktailmemo getCocktailmemo(int cocktailId) {
		CocktailmemoDAO cocktailmemoDAO = _db.createCocktailmemoDAO();
		ListenableFuture<Cocktailmemo> future = cocktailmemoDAO.findByPK(cocktailId);
		Cocktailmemo cocktailmemo = null;
		try {
			cocktailmemo = future.get();
		}
		catch(ExecutionException ex) {
			ex.printStackTrace();
		}
		catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		return cocktailmemo;
	}

	public boolean deleteInsertCocktailmemo(Cocktailmemo cocktailmemo) {
		CocktailmemoDAO cocktailmemoDAO = _db.createCocktailmemoDAO();
		ListenableFuture<Integer> futureDelete = cocktailmemoDAO.delete(cocktailmemo);
		boolean result = false;
		try {
			int resultDelete = futureDelete.get();
			if(resultDelete >= 0) {
				ListenableFuture<Long> futureInsert = cocktailmemoDAO.insert(cocktailmemo);
				long resultInsert = futureInsert.get();
				if(resultInsert >= 0) {
					result = true;
				}
			}
		}
		catch(ExecutionException ex) {
			ex.printStackTrace();
		}
		catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
