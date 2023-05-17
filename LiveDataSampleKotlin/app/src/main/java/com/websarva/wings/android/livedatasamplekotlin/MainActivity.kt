package com.websarva.wings.android.livedatasamplekotlin

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.websarva.wings.android.livedatasamplekotlin.ui.MainViewModel
import com.websarva.wings.android.repositorysamplekotlin.data.local.Cocktailmemo

class MainActivity : AppCompatActivity() {
	private val _mainViewModel by viewModels<MainViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		_mainViewModel.cocktailmemoLiveData.observe(this@MainActivity, CocktailmemoObserver())
		val lvCocktail = findViewById<ListView>(R.id.lvCocktail)
		lvCocktail.onItemClickListener = ListItemClickListener()
	}

	fun onSaveButtonClick(view: View) {
		val etNote = findViewById<EditText>(R.id.etNote)
		val note = etNote.text.toString()
		_mainViewModel.saveCocktailmemo(note)
	}

	private inner class ListItemClickListener : AdapterView.OnItemClickListener {
		override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
			val cocktailName = parent.getItemAtPosition(position) as String
			_mainViewModel.prepareCocktailmemo(position, cocktailName)
		}
	}

	private inner class CocktailmemoObserver : Observer<Cocktailmemo> {
		override fun onChanged(cocktailmemo: Cocktailmemo) {
			val tvCocktailName = findViewById<TextView>(R.id.tvCocktailName)
			tvCocktailName.text = cocktailmemo.name
			val etNote = findViewById<EditText>(R.id.etNote)
			etNote.setText(cocktailmemo.note)
			val btnSave = findViewById<Button>(R.id.btnSave)
			if(cocktailmemo.id == -1) {
				etNote.isEnabled = false
				btnSave.isEnabled = false
			}
			else {
				etNote.isEnabled = true
				btnSave.isEnabled = true
			}
		}
	}
}
