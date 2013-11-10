package com.feedbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class GrupoActivity extends Activity {

	private List<Map<String, Object>> grupoList;

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_grupo);
		listView = (ListView) findViewById(R.id.listaGrupo);
		String[] de = { "nomeGrupo" };
		int[] para = { R.id.nomeGrupo };
		SimpleAdapter adapter = new SimpleAdapter(this, listarGrupos(),
				R.layout.grupo, de, para);
		listView.setAdapter(adapter);
		registerForContextMenu(listView);
	}

	public List<Map<String, Object>> listarGrupos() {
		grupoList = new ArrayList<Map<String, Object>>();

		Map<String, Object> grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "IHC Tarde 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "Emp Noite 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "HSI Noite 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "LBD Noite 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "RH Noite 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "ERP Noite 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "Pro Micro Noite 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "IHC Tarde 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "Emp Noite 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "HSI Noite 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "LBD Noite 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "RH Noite 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "ERP Noite 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "Pro Micro Noite 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "IHC Tarde 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "Emp Noite 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "HSI Noite 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "LBD Noite 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "RH Noite 2013 - 2 Semestre");
		grupoList.add(grupo);

		grupo = new HashMap<String, Object>();
		grupo.put("nomeGrupo", "ERP Noite 2013 - 2 Semestre");
		grupoList.add(grupo);
		
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
		if (item.getItemId() == R.id.novoFeed) {
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
					.getMenuInfo();

			startActivity(new Intent(this, NovoFeedActivity.class));
			return true;
		}
		if (item.getItemId() == R.id.feedsGrupo) {
			Toast.makeText(this, "Feeds do Grupo",  Toast.LENGTH_SHORT).show();
			return true;
		}
		if (item.getItemId() == R.id.excluirGrupo) {
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
					.getMenuInfo();
			grupoList.remove(info.position);
			listView.invalidateViews();
			return true;
		}
		return super.onContextItemSelected(item);
	}
	
	public void novoGrupo(View view){
		startActivity(new Intent(this, NovoGrupoActivity.class));
	}

}
