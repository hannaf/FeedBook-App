package com.feedbook;

import com.feedbook.MainActivity;
import com.feedbook.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;


public class MainActivity extends Activity /* implements OnClickListener */{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Button btnEntra = (Button) findViewById(R.id.btnEntra);
		final EditText edtxtPwd = (EditText) findViewById(R.id.edtxtPwd);
		final EditText edtxtUser = (EditText) findViewById(R.id.edtxtUser);
		final TextView edtxCadastrar = (TextView) findViewById(R.id.cadastrar);

		
		btnEntra.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String Login = "hanna";
				String Senha = "1234";
				String strLogin = edtxtUser.getText().toString();
				String strSenha = edtxtPwd.getText().toString();

				if (v.getId() == R.id.btnEntra) {

					int L = strLogin.length();
					if (L < 1) {
						Toast.makeText(MainActivity.this, "Falta o usuário",
								Toast.LENGTH_LONG).show();
					}

					if (v.getId() == R.id.btnEntra) {
						String Pwd = edtxtPwd.getText().toString();
						int R = Pwd.length();
						if (R < 1) {
							Toast.makeText(MainActivity.this, "Falta a senha",
									Toast.LENGTH_LONG).show();
						}
					}

					if (strLogin.equals(Login) && strSenha.equals(Senha)) {

						Toast.makeText(MainActivity.this,
								"Login com Sucesso!!!", Toast.LENGTH_SHORT)
								.show();

						Intent intent = new Intent(MainActivity.this,
								FeedActivity.class);
						startActivity(intent);
					} else
						Toast.makeText(MainActivity.this,
								"Erro de login, usuário ou senha inválidos",
								Toast.LENGTH_SHORT).show();

				}
			}
		});
		
		edtxCadastrar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MainActivity.this, CadastroUsuarioActivity.class));				
			}
			
		});

		// final de oncreate

	}

}// final main activity

