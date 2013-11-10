package com.feedbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NovoFeedActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.novo_feed);
	}
	
	public void publicarFeed(View view){
		EditText edtTitulo = (EditText) findViewById(R.id.tituloFeed);
		EditText edtDetalhe = (EditText) findViewById(R.id.detalheFeed);
		String titulo = edtTitulo.getText().toString();
		String detalhe = edtDetalhe.getText().toString(); //(Criar caixa de confirmação)
		
		if(titulo.length() == 0){
			Toast.makeText(this, R.string.msg_digite_titulo, Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, R.string.msg_feed_publicado_sucesso, Toast.LENGTH_SHORT).show();
		}
	}

}
