package com.feedbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroUsuarioActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_usuario);
	}

	public void continuar(View view){
		EditText edtEmail = (EditText) findViewById(R.id.emailUsuario);
		EditText edtApelido = (EditText) findViewById(R.id.apelidoUsuario);
		String email = edtEmail.getText().toString();
		String apelido = edtApelido.getText().toString();
		
		if (email.length() == 0){
			Toast.makeText(this, R.string.msg_digite_email_valido, Toast.LENGTH_SHORT).show();
		} else if (apelido.length() == 0){
			Toast.makeText(this, R.string.msg_digite_apelido, Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, R.string.msg_insira_codigo_email, Toast.LENGTH_SHORT).show();
			startActivity(new Intent(this, ConfirmacaoCadastroActivity.class));
		}
	}
	
}