package com.websarva.wings.android.livedatasamplejava.ui;

import android.app.Application;

import com.websarva.wings.android.livedatasamplejava.data.CocktailmemoRepository;
import com.websarva.wings.android.livedatasamplejava.data.local.Cocktailmemo;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainViewModel extends AndroidViewModel {
	private CocktailmemoRepository _cocktailmemoRepository;
	private MutableLiveData<Cocktailmemo> _cocktailmemoLiveData = new MutableLiveData<>();

	public MainViewModel(Application application) {
		super(application);
		_cocktailmemoRepository = new CocktailmemoRepository(application);
		Cocktailmemo cocktailmemo = new Cocktailmemo();
		cocktailmemo.id = -1;
		cocktailmemo.name = "未選択";
		cocktailmemo.note = "";
		_cocktailmemoLiveData.setValue(cocktailmemo);
	}

	public void prepareCocktailmemo(int cocktailId, String cocktailName) {
		Cocktailmemo cocktailmemo = _cocktailmemoRepository.getCocktailmemo(cocktailId);
		if(cocktailmemo == null) {
			cocktailmemo = new Cocktailmemo();
			cocktailmemo.id = cocktailId;
			cocktailmemo.name = cocktailName;
			cocktailmemo.note = "";
		}
		_cocktailmemoLiveData.setValue(cocktailmemo);
	}

	public void saveCocktailmemo(String note) {
		Cocktailmemo cocktailmemo = _cocktailmemoLiveData.getValue();
		cocktailmemo.note = note;
		boolean result = _cocktailmemoRepository.deleteInsertCocktailmemo(cocktailmemo);
		if(result) {
			cocktailmemo.id = -1;
			cocktailmemo.name = "未選択";
			cocktailmemo.note = "";
			_cocktailmemoLiveData.setValue(cocktailmemo);
		}
	}

	public LiveData<Cocktailmemo> getCocktailmemoLiveData() {
		return _cocktailmemoLiveData;
	}
}
