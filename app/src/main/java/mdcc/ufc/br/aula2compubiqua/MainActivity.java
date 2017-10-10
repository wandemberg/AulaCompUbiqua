package mdcc.ufc.br.aula2compubiqua;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import mdcc.ufc.br.CalculaImc;

public class MainActivity extends AppCompatActivity {

    Button btCalcular;
    EditText peso, altura;
    Spinner sexo;
    TextView  historico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        peso = (EditText) findViewById(R.id.campoPeso);
        altura = (EditText) findViewById(R.id.campoAltura);
        sexo = (Spinner) findViewById(R.id.spinnerSexo);
        btCalcular = (Button) findViewById(R.id.btCalcular);
        historico = (TextView) findViewById(R.id.historico);


        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pesoString = MainActivity.this.peso.getText().toString();
                float peso = Float.parseFloat(pesoString);
                float altura = Float.parseFloat(MainActivity.this.altura.getText().toString());
                int sexo = MainActivity.this.sexo.getSelectedItemPosition();

                Bundle bundle = new Bundle();
                bundle.putFloat("peso", peso);
                bundle.putFloat("altura", altura);
                bundle.putInt("sexo", sexo);

                Intent intenteCalcula = new Intent(MainActivity.this, CalculaImc.class);
                intenteCalcula.putExtras(bundle);

                startActivityForResult(intenteCalcula, 123);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                boolean teste = data.hasExtra("retorno");
                String resultado = data.getExtras().getString("retorno");
                historico.setText(resultado);
            } else {
                // usuário não tirou foto.
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
