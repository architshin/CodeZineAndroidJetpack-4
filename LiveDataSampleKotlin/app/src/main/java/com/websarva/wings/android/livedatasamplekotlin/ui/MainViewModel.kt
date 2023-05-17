package com.websarva.wings.android.livedatasamplekotlin.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.websarva.wings.android.livedatasamplekotlin.data.CocktailmemoRepository
import com.websarva.wings.android.repositorysamplekotlin.data.local.Cocktailmemo
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
	private val _cocktailmemoRepository: CocktailmemoRepository
	val cocktailmemoLiveData = MutableLiveData<Cocktailmemo>()

	init {
		_cocktailmemoRepository = CocktailmemoRepository(application)
		val cocktailmemo = Cocktailmemo(-1, "未選択", "")
		cocktailmemoLiveData.value = cocktailmemo
	}

	fun prepareCocktailmemo(cocktailId: Int, cocktailName: String) {
		viewModelScope.launch {
			var cocktailmemo = _cocktailmemoRepository.getCocktailmemo(cocktailId)
			if(cocktailmemo == null) {
				cocktailmemo = Cocktailmemo(cocktailId, cocktailName, "")
			}
			cocktailmemoLiveData.value = cocktailmemo
		}
	}

	fun saveCocktailmemo(note: String) {
		var cocktailmemo = Cocktailmemo(cocktailmemoLiveData.value!!.id, cocktailmemoLiveData.value!!.name, note)
		viewModelScope.launch {
			val result = _cocktailmemoRepository.deleteInsertCocktailmemo(cocktailmemo)
			if(result) {
				cocktailmemo = Cocktailmemo(-1, "未選択", "")
				cocktailmemoLiveData.value = cocktailmemo
			}
		}
	}
}
