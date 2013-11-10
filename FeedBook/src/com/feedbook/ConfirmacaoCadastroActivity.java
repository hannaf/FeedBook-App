package com.feedbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConfirmacaoCadastroActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirmacao_cadastro);
	}
	
	public void continuar(View view){
		EditText edtCodigo = (EditText) findViewById(R.id.codigoEmail);
		String codigo = edtCodigo.getText().toString();
		
		if (codigo.length() == 0){
			Toast.makeText(this, R.string.msg_insira_codigo_email, Toast.LENGTH_SHORT).show();
		} else if (!codigo.equals("")){
			Toast.makeText(this, R.string.msg_codigo_incorreto, Toast.LENGTH_SHORT).show();
		} else {
			startActivity(new Intent(this, AlterarSenhaActivity.class));			
		}
	}
}
