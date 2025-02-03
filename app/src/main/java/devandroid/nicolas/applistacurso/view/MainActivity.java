package devandroid.nicolas.applistacurso.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import devandroid.nicolas.applistacurso.Controller.PessoaController;
import devandroid.nicolas.applistacurso.R;
import devandroid.nicolas.applistacurso.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    public static final String NOME_PREFERENCES = "pref_listavip";

    PessoaController controller;
    Pessoa pessoa;
    Pessoa outrapessoa;

    EditText editPrimeiroNome;
    EditText editSobrenomeAluno;
    EditText editNomeCurso;
    EditText editTelefoneContato;

    Button btnLimpar;
    Button btnSalvar;
    Button btnFinalizar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        preferences = getSharedPreferences(NOME_PREFERENCES,0);
        SharedPreferences.Editor listaVip = preferences.edit();

        controller = new PessoaController();
        controller.toString();

        pessoa = new Pessoa();

        outrapessoa = new Pessoa();

        outrapessoa.setPrimeiroNome("Domicio");
        outrapessoa.setSobreNome("Fonseca");
        outrapessoa.setCursoDesejado("Capinar Deitado");
        outrapessoa.setTelefoneContato("11-223355444");

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobrenomeAluno = findViewById(R.id.editSobrenomeAluno);
        editNomeCurso = findViewById(R.id.editNomeCurso);
        editTelefoneContato = findViewById(R.id.editTelefoneContato);

        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        editPrimeiroNome.setText(outrapessoa.getPrimeiroNome());
        editSobrenomeAluno.setText(outrapessoa.getSobreNome());
        editNomeCurso.setText(outrapessoa.getCursoDesejado());
        editTelefoneContato.setText(outrapessoa.getTelefoneContato());

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPrimeiroNome.setText("");
                editSobrenomeAluno.setText("");
                editNomeCurso.setText("");
                editTelefoneContato.setText("");
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Volte Sempre", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pessoa.setPrimeiroNome(editPrimeiroNome.getText().toString());
                pessoa.setSobreNome(editSobrenomeAluno.getText().toString());
                pessoa.setCursoDesejado(editNomeCurso.getText().toString());
                pessoa.setTelefoneContato(editTelefoneContato.getText().toString());

                Toast.makeText(MainActivity.this, "Salvo" + pessoa.toString(), Toast.LENGTH_LONG).show();

                listaVip.putString("primeiroNome",pessoa.getPrimeiroNome());
                listaVip.putString("sobreNOme",pessoa.getSobreNome());
                listaVip.putString("nomeCurso",pessoa.getCursoDesejado());
                listaVip.putString("telefoneContato",pessoa.getTelefoneContato());
                listaVip.apply();

                controller.salvar(pessoa);
            }
        });

        Log.i("POOAndroid", "Objeto pessoa: " + pessoa.toString());
        Log.i("POOAndroid", "Objeto outrapessoa: " + outrapessoa.toString());
    }
}