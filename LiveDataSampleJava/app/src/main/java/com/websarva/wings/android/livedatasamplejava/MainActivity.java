package com.websarva.wings.android.livedatasamplejava;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.websarva.wings.android.livedatasamplejava.data.local.Cocktailmemo;
import com.websarva.wings.android.livedatasamplejava.ui.MainViewModel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
	private MainViewModel _mainViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewModelProvider provider = new ViewModelProvider(MainActivity.this);
		_mainViewModel = provider.get(MainViewModel.class);
		LiveData<Cocktailmemo> cocktailmemoLiveData = _mainViewModel.getCocktailmemoLiveData();
		cocktailmemoLiveData.observe(MainActivity.this, new CocktailmemoObserver());
		ListView lvCocktail = findViewById(R.id.lvCocktail);
		lvCocktail.setOnItemClickListener(new ListItemClickListener());
	}

	public void onSaveButtonClick(View view) {
		EditText etNote = findViewById(R.id.etNote);
		String note = etNote.getText().toString();
		_mainViewModel.saveCocktailmemo(note);
	}

	private class ListItemClickListener implements AdapterView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			String cocktailName = (String) parent.getItemAtPosition(position);
			_mainViewModel.prepareCocktailmemo(position, cocktailName);
		}
	}

	private class CocktailmemoObserver implements Observer<Cocktailmemo> {
		@Override
		public void onChanged(@NonNull Cocktailmemo cocktailmemo) {
			TextView tvCocktailName = findViewById(R.id.tvCocktailName);
			tvCocktailName.setText(cocktailmemo.name);
			EditText etNote = findViewById(R.id.etNote);
			etNote.setText(cocktailmemo.note);
			Button btnSave = findViewById(R.id.btnSave);
			if(cocktailmemo.id == -1) {
				etNote.setEnabled(false);
				btnSave.setEnabled(false);
			}
			else {
				etNote.setEnabled(true);
				btnSave.setEnabled(true);
			}
		}
	}
}
