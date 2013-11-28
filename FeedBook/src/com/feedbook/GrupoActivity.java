package com.feedbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.feedbook.domain.Grupo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class GrupoActivity extends Activity {

	private List<Map<String, Object>> grupoList;

	private ListView listView;

	private DatabaseHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_grupo);
		dbHelper = new DatabaseHelper(this);
		listView = (ListView) findViewById(R.id.listaGrupo);
		String[] de = { "nomeGrupo" };
		int[] para = { R.id.nomeGrupo };
		SimpleAdapter adapter = new SimpleAdapter(this, listarGrupos(),
				R.layout.grupo, de, para);
		listView.setAdapter(adapter);
		registerForContextMenu(listView);
	}

	public List<Map<String, Object>> listarGrupos() {
		grupoList = new ArrayList<Map<String,Object>>();
		Map<String,Object> grupoItem;
		Grupo grupo;
		Long id = getIntent().getLongExtra("idUsuario", 0);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT _id, nome_grupo, descricao_grupo, " +
		"chave_grupo, status FROM grupo g LEFT OUTER JOIN grupo_usuario gu ON g._id = gu.id_grupo WHERE gu.id_usuario = ? AND " +
		" g.inativo != ? AND gu.status != ? ",
		new String[]{String.valueOf(id), ConstantesUtil.GRUPO_INATIVO, String.valueOf(ConstantesUtil.STATUS_FEED_CANCELADO) });
		cursor.moveToFirst();
		for (int i = 0; i < cursor.getCount(); i++) {
			grupoItem = new HashMap<String, Object>();
			grupo = new Grupo();
			grupo.setId(cursor.getLong(0));
			grupo.setNome(cursor.getString(1));
			grupo.setDescricao(cursor.getString(2));
			grupo.setChave(cursor.getString(3));
			grupo.setStatus(cursor.getInt(4));
			grupoItem.put("nomeGrupo", cursor.getString(1));
			grupoItem.put("grupoItem", grupo);
			grupoList.add(grupoItem);
			cursor.moveToNext();
		}
		cursor.close();
		
		return grupoList;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_grupo, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		Grupo grupo = (Grupo) listarGrupos().get(info.position).get("grupoItem");
		if (item.getItemId() == R.id.novoFeed) {
			Intent intent = new Intent(this, NovoFeedActivity.class);
			intent.putExtra("idGrupo", grupo.getId());
			intent.putExtra("nomeGrupo", grupo.getNome());
			startActivity(intent);
			finish();
			return true;
		}
		if (item.getItemId() == R.id.feedsGrupo) {
			Intent intent = new Intent(this, FeedActivity.class);
			//id pesquisar
			startActivity(intent);			
			return true;
		}
		if (item.getItemId() == R.id.excluirGrupo) {
			if(grupo.getStatus() == ConstantesUtil.STATUS_MODERADOR){
				//Criar caixa de dialogo
				publicafeedAviso(grupo.getId());
				exclusaoAdministrador(grupo);
				grupoList.remove(info.position);
				listView.invalidateViews();
			} else {
				//Criar caixa de dialogo
				exclusaoAssinante(grupo);
				grupoList.remove(info.position);
				listView.invalidateViews();
			}
			ToastManager.show(this, getString(R.string.grupo_excluido_sucesso), ToastManager.SUCCESS);
			return true;
		}
		return super.onContextItemSelected(item);
	}

	public void novoGrupo(View view) {
		Intent intent = new Intent(this, NovoGrupoActivity.class);
		intent.putExtra("idUsuario", getIntent().getLongExtra("idUsuario", 1));
		startActivity(intent);
		finish();		
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
	
	public void publicafeedAviso(Long id){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();		
		values.put("titulo_feed", getString(R.string.grupo_excluido));
		values.put("detalhe_feed", getString(R.string.descricao_grupo_excluido));
		values.put("id_grupo", id);
		db.insert("feed", null, values);	
	}
	
	public void exclusaoAdministrador(Grupo grupo){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("nome_grupo", grupo.getNome());
		values.put("descricao_grupo", grupo.getDescricao());
		values.put("chave_grupo", grupo.getChave());
		values.put("inativo", ConstantesUtil.GRUPO_INATIVO);
		db.update("grupo", values, "_id = ?",new String[]{ grupo.getId().toString() });
	}
	
	public void exclusaoAssinante(Grupo grupo){
		Long id = getIntent().getLongExtra("idUsuario", 0);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("id_grupo", grupo.getId()); 
		values.put("id_usuario", id); 
		values.put("status", ConstantesUtil.STATUS_FEED_CANCELADO);
		db.update("grupo_usuario", values, "id_grupo = ? AND id_usuario = ?",new String[]{ grupo.getId().toString(), id.toString()});
		
	}

}


