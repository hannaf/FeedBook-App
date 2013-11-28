package com.feedbook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NovoFeedActivity extends Activity {
	
	private DatabaseHelper dbHelper;
	
	private TextView txVNomeGrupo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.novo_feed);
		dbHelper = new DatabaseHelper(this);
		txVNomeGrupo = (TextView) findViewById(R.id.nomeGrupo);
		txVNomeGrupo.setText(getIntent().getStringExtra("nomeGrupo"));
		
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
			Long id = getIntent().getLongExtra("idGrupo", 0);
			
			values.put("titulo_feed",titulo);
			values.put("detalhe_feed", detalhe);
			values.put("id_grupo", id);
			
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
	
	public void onBackPressed() {
		 startActivity(new Intent(this, FeedActivity.class)
		 .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
		 finish();
		 return;
		}
	

}
