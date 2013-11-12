package com.feedbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
			ToastManager.show(this, getString(R.string.msg_digite_titulo), ToastManager.ERROR);
		} else {
			ToastManager.show(this, getString(R.string.msg_feed_publicado_sucesso), ToastManager.SUCCESS);
		}
	}

}
