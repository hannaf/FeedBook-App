package com.feedbook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AlterarSenhaActivity extends Activity {

	private DatabaseHelper databaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alterar_senha);
		databaseHelper = new DatabaseHelper(this);
	}

	public void salvarUsuario(View view) {
		EditText edtSenha = (EditText) findViewById(R.id.senhaUsuario);
		EditText edtConfima = (EditText) findViewById(R.id.confirmarSenha);
		String senha = edtSenha.getText().toString();
		String confirma = edtConfima.getText().toString();

		if (senha.length() == 0) {
			Toast.makeText(this, R.string.msg_digite_senha, Toast.LENGTH_SHORT).show();
		} else if (senha.length() < 6) {
			Toast.makeText(this, R.string.msg_senha_seis_digitos, Toast.LENGTH_SHORT).show();
		} else if (confirma.length() == 0) {
			Toast.makeText(this, R.string.msg_digite_confirmacao, Toast.LENGTH_SHORT).show();
		} else if (!confirma.equals(senha)) {
			Toast.makeText(this, R.string.msg_confirmacao_incorreta, Toast.LENGTH_SHORT).show();
		} else {

			SQLiteDatabase db = databaseHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("email_usuario", "");
			values.put("apelido", "");
			values.put("senha", senha);
			
			long resultado = db.insert("usuario", null, values);
			
			if (resultado != -1) {
				Toast.makeText(this, R.string.msg_usuario_cadastrado_sucesso, Toast.LENGTH_SHORT).show();
				startActivity(new Intent(this, FeedActivity.class));
			}
		}		
	}

}
