package com.feedbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class FeedActivity extends Activity implements OnItemClickListener {
	
	private List<Map<String,Object>> feedList;
	
	private ListView listView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_feed);
		listView = (ListView) findViewById(R.id.listaFeed);
		String[] de = {"nomeGrupo","tituloFeed","detalhe"};
		int[] para = {R.id.nomeGrupo, R.id.tituloFeed, R.id.detalhe};
		SimpleAdapter adapter = new SimpleAdapter(this, listarFeeds(), R.layout.feed, de, para);
		listView.setAdapter(adapter);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	public List<Map<String,Object>> listarFeeds(){
		feedList = new ArrayList<Map<String,Object>>();
		Map<String,Object> feed = new HashMap<String, Object>();
		feed.put("nomeGrupo", "IHC Noite 2013 - 2 Semestre");
		feed.put("tituloFeed", "Trabalho t2");
		feed.put("detalhe", "Trabalho t2 entrega no dia xx/xx.");
		feedList.add(feed);
		
		feed = new HashMap<String, Object>();
		feed.put("nomeGrupo", "LBD Noite 2013 - 2 Semestre");
		feed.put("tituloFeed", "Não haverá aula");
		feed.put("detalhe", "Próxima aula dia xx/xx não haverá aula de LBD, os trabalhos deverão ser entreges na semana seguinte.");
		feedList.add(feed);
		
		feed = new HashMap<String, Object>();
		feed.put("nomeGrupo", "IHC Tarde 2013 - 2 Semestre");
		feed.put("tituloFeed", "Alteração data prova");
		feed.put("detalhe", "A data da prova P2 foi alterada do dia xx/xx para o dia xx/xx.");
		feedList.add(feed);
		
		feed = new HashMap<String, Object>();
		feed.put("nomeGrupo", "Soft Livre Noite 2013 - 2 Semestre");
		feed.put("tituloFeed", "Não haverá aula");
		feed.put("detalhe", "Hoje não haverá aula de Software livre.");
		feedList.add(feed);
		
		feed = new HashMap<String, Object>();
		feed.put("nomeGrupo", "IHC Noite 2013 - 2 Semestre");
		feed.put("tituloFeed", "Trabalho t2");
		feed.put("detalhe", "Trabalho t2 entrega no dia xx/xx.");
		feedList.add(feed);
		
		feed = new HashMap<String, Object>();
		feed.put("nomeGrupo", "LBD Noite 2013 - 2 Semestre");
		feed.put("tituloFeed", "Não haverá aula");
		feed.put("detalhe", "Próxima aula dia xx/xx não haverá aula de LBD, os trabalhos deverão ser entreges na semana seguinte.");
		feedList.add(feed);
		
		feed = new HashMap<String, Object>();
		feed.put("nomeGrupo", "IHC Tarde 2013 - 2 Semestre");
		feed.put("tituloFeed", "Alteração data prova");
		feed.put("detalhe", "A data da prova P2 foi alterada do dia xx/xx para o dia xx/xx.");
		feedList.add(feed);
		
		feed = new HashMap<String, Object>();
		feed.put("nomeGrupo", "Soft Livre Noite 2013 - 2 Semestre");
		feed.put("tituloFeed", "Não haverá aula");
		feed.put("detalhe", "Hoje não haverá aula de Software livre.");
		feedList.add(feed);
		
		feed = new HashMap<String, Object>();
		feed.put("nomeGrupo", "IHC Noite 2013 - 2 Semestre");
		feed.put("tituloFeed", "Trabalho t2");
		feed.put("detalhe", "Trabalho t2 entrega no dia xx/xx.");
		feedList.add(feed);
		
		feed = new HashMap<String, Object>();
		feed.put("nomeGrupo", "LBD Noite 2013 - 2 Semestre");
		feed.put("tituloFeed", "Não haverá aula");
		feed.put("detalhe", "Próxima aula dia xx/xx não haverá aula de LBD, os trabalhos deverão ser entreges na semana seguinte.");
		feedList.add(feed);
		
		feed = new HashMap<String, Object>();
		feed.put("nomeGrupo", "IHC Tarde 2013 - 2 Semestre");
		feed.put("tituloFeed", "Alteração data prova");
		feed.put("detalhe", "A data da prova P2 foi alterada do dia xx/xx para o dia xx/xx.");
		feedList.add(feed);
		
		feed = new HashMap<String, Object>();
		feed.put("nomeGrupo", "Soft Livre Noite 2013 - 2 Semestre");
		feed.put("tituloFeed", "Não haverá aula");
		feed.put("detalhe", "Hoje não haverá aula de Software livre.");
		feedList.add(feed);
		
		
		return feedList;
	}
	
	public void listarGrupos(View view){
		startActivity(new Intent(this, GrupoActivity.class));
	}

	
	public void onItemClick(android.widget.AdapterView<?> adapter, View view, int position, long id) {
		Map<String, Object> map = feedList.get(position);
		String destino = (String) map.get("nomeGrupo");
		String mensagem = "Nome grupo: "+ destino;
		Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
	}

}
