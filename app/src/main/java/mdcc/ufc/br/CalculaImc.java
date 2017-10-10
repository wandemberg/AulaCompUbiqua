package mdcc.ufc.br;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import mdcc.ufc.br.aula2compubiqua.R;

public class CalculaImc extends AppCompatActivity {

    Button btOk;
    float resultadoImc;
    String mensagemResultado;
    String resultadoIntent;
    TextView resultado;
    ImageView imagemResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcula_imc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        float peso = getIntent().getExtras().getFloat("peso");
        float altura = getIntent().getExtras().getFloat("altura")/100;
        int sexo = getIntent().getExtras().getInt("sexo");

        resultado = (TextView) findViewById(R.id.resultadoImc);

        imagemResultado = (ImageView) findViewById(R.id.imageResultado);

        resultadoImc = peso/(altura*altura);

        if (sexo == 0) {//Masculino
           // imagemResultado.setVisibility(View.GONE);
            if (resultadoImc <= 18.5) {
                mensagemResultado = "Abaixo do peso";
                imagemResultado.setImageResource(R.drawable.abaixo_peso_masculino);
            } else if (resultadoImc > 18.5 && resultadoImc <= 24.9) {
                mensagemResultado = "Peso ideal";
                imagemResultado.setImageResource(R.drawable.peso_normal_masculino);
            } else if (resultadoImc > 24.9 && resultadoImc <= 29.9) {
                mensagemResultado = "Sobrepeso";
                imagemResultado.setImageResource(R.drawable.acima_peso_masculino);
            } else if (resultadoImc > 29.9 && resultadoImc <= 34.9) {
                mensagemResultado = "Obesidade grau I";
                imagemResultado.setImageResource(R.drawable.obesidade_1_masculino);
            } else if (resultadoImc > 34.9 && resultadoImc <= 39.9) {
                mensagemResultado = "Obesidade grau II";
                imagemResultado.setImageResource(R.drawable.obesidade_2_masculino);
            } else if (resultadoImc > 39.9) {
                mensagemResultado = "Obesidade grau III";
                imagemResultado.setImageResource(R.drawable.obesidade_3_masculino);
            }
        }else {//Feminino
            if (resultadoImc <= 18.5) {
                mensagemResultado = "Abaixo do peso";
                imagemResultado.setImageResource(R.drawable.abaixo_peso_imc);
            } else if (resultadoImc > 18.5 && resultadoImc <= 24.9) {
                mensagemResultado = "Peso ideal";
                imagemResultado.setImageResource(R.drawable.peso_normal_imc);
            } else if (resultadoImc > 24.9 && resultadoImc <= 29.9) {
                mensagemResultado = "Sobrepeso";
                imagemResultado.setImageResource(R.drawable.acima_peso_imc);
            } else if (resultadoImc > 29.9 && resultadoImc <= 34.9) {
                mensagemResultado = "Obesidade grau I";
                imagemResultado.setImageResource(R.drawable.obesidade_1_imc);
            } else if (resultadoImc > 34.9 && resultadoImc <= 39.9) {
                mensagemResultado = "Obesidade grau II";
                imagemResultado.setImageResource(R.drawable.obesidade_2_imc);
            } else if (resultadoImc > 39.9) {
                mensagemResultado = "Obesidade grau III";
                imagemResultado.setImageResource(R.drawable.obesidade_3_imc);
            }
        }

        resultado.setText(mensagemResultado);

        btOk = (Button) findViewById(R.id.btOk);

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String retorno = "IMC antigo é " + resultadoImc + " - " + mensagemResultado;
                Bundle bundle = new Bundle();
                Intent it = new Intent();
                try{
                    bundle.putString("retorno", retorno);
                    it.putExtras(bundle);
                    setResult(RESULT_OK, it);
                }catch(Exception ex){
                    setResult(RESULT_CANCELED);
                }
                finish();

            }
        });



    }

}
