package com.feedbook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NovoFeedActivity extends Activity {
	
	private DatabaseHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.novo_feed);
		dbHelper = new DatabaseHelper(this);
		
	}
	
	public void publicarFeed(View view){
		EditText edtTitulo = (EditText) findViewById(R.id.tituloFeed);
		EditText edtDetalhe = (EditText) findViewById(R.id.detalheFeed);
		String titulo = edtTitulo.getText().toString();
		String detalhe = edtDetalhe.getText().toString();
		
		if(titulo.length() == 0){
			ToastManager.show(this, getString(R.string.msg_digite_titulo), ToastManager.ERROR);
		} else {
			
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			
			values.put("titulo_feed",titulo);
			values.put("detalhe_feed", detalhe);
			values.put("id_grupo",1);
			
			long resultado = db.insert("feed", null, values);
			
			
			if (resultado != -1) {
				ToastManager.show(this, getString(R.string.msg_feed_publicado_sucesso), ToastManager.SUCCESS);
				startActivity(new Intent(this, FeedActivity.class));
				finish();
			}
			
		}
	}
	
	
	
	protected void onDestroy() {
		dbHelper.close();
		super.onDestroy();
	}
	

}
