package com.feedbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class NovoGrupoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.novo_grupo);
	}
	
	public void publicarFeed(View view){
		Toast.makeText(this, "Arquivo publicado com sucesso!", Toast.LENGTH_SHORT);
	}
	
}
