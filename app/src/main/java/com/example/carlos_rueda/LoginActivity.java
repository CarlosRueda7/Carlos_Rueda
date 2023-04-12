package com.example.carlos_rueda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuario, etcontraseña;
    private SharedPreferences misPreferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        referenciar();
        misPreferencias = getSharedPreferences("tienda_app", MODE_PRIVATE);

        //VERIFICAR SI ESTA LOGUEADO

        if (misPreferencias.getBoolean("logueado", false)) {
            Intent miIntent = new Intent(this, MainActivity.class);
            startActivity(miIntent);
            finish();

        }

    }

    private void referenciar() {
        etUsuario = findViewById(R.id.etlogin);
        etcontraseña = findViewById(R.id.etcontraseña);
    }

    public void clickIniciarSesion(View view) {
        
        String PASS = "123456";
        String USER = "carlos";
        String passUser = etcontraseña.getText().toString();
        String userUser = etUsuario.getText().toString();

        if (PASS.equals(passUser) && USER.equals(userUser)) {

            SharedPreferences.Editor myEditor = misPreferencias.edit();
            myEditor.putBoolean("logueado", true);
            myEditor.apply();

            Intent miIntent = new Intent(this, MainActivity.class);
            startActivity(miIntent);
            finish();
        } else {
            Toast.makeText(this, "CRENDENCIALES INCORRECTAS", Toast.LENGTH_SHORT).show();

        }
    }

}