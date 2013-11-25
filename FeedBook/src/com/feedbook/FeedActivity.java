package com.feedbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;



public class FeedActivity extends Activity {
	
	private List<Map<String,Object>> feedList;
	
	private ListView listView;
	
	
	private DatabaseHelper dbHelper;
	
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		setContentView(R.layout.lista_feed);
		listView = (ListView) findViewById(R.id.listaFeed);
		dbHelper = new DatabaseHelper(this);
		String[] de = {"nomeGrupo","tituloFeed"};
		int[] para = {R.id.nomeGrupo, R.id.tituloFeed};
		SimpleAdapter adapter = new SimpleAdapter(this, listarFeeds(), R.layout.feed, de, para);
		listView.setAdapter(adapter);
		 adapter.notifyDataSetChanged();
		 
	}
	
	
	
	public List<Map<String,Object>> listarFeeds(){
		feedList = new ArrayList<Map<String,Object>>();
		Map<String,Object> feed;
		int id = getIntent().getIntExtra("idUsuario", 0);
		
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT nome_grupo , titulo_feed , detalhe_feed  FROM feed AS F " +
				"INNER JOIN GRUPO AS G ON G._ID = F.ID_GRUPO " +
				"INNER JOIN GRUPO_USUARIO AS GU ON GU.id_grupo = G._ID " +
				"WHERE GU.ID_USUARIO = ? ",
		new String[]{String.valueOf(id)});
		cursor.moveToFirst();
		for (int i = 0; i < cursor.getCount(); i++) {
			feed = new HashMap<String, Object>();
			feed.put("nomeGrupo", cursor.getString(1));
			feed.put("tituloFeed", cursor.getString(1));
			feed.put("detalhe", cursor.getString(1));
			feedList.add(feed);
			cursor.moveToNext();
		}
		cursor.close();
		
		return feedList;
	}
	
	public void listarGrupos(View view){
		Intent intent = new Intent(this, GrupoActivity.class);
		intent.putExtra("idUsuario", getIntent().getIntExtra("idUsuario", 0));
		startActivity(intent);
	}
	
	private Toast toast;
	private long lastBackPressTime = 0;
	 
	@Override
	public void onBackPressed() {
	  if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
	    toast = Toast.makeText(this, "Pressione o Botão Voltar novamente para fechar o Aplicativo.", 4000);
	    toast.show();
	    this.lastBackPressTime = System.currentTimeMillis();
	  } else {
	    if (toast != null) {
	    toast.cancel();
	  }
	  super.onBackPressed();
	 }
	}





}
