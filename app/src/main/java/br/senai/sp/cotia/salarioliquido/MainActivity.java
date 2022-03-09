package br.senai.sp.cotia.salarioliquido;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity /*implements AdapterView.OnItemSelectedListener*/ {

    private TextView vale_refeicao, vale_transp, vale_alm, planoSaude, inssR, irrfR;
    private Button calcular;
    private EditText editSalBrut;
    private EditText editNumDep;
    private double plnSaud;
    private RadioGroup groupRef, groupAli, groupTrans;
    double valAlm = 0, valRef =0, valTrp = 0;
    private Spinner spinner;
    private double inss, irrf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //chamando class funcionario
        editSalBrut = findViewById(R.id.edit_SalBrut);
        editNumDep = findViewById(R.id.edit_NumDep);
        calcular = findViewById(R.id.botao);
        groupAli = findViewById(R.id.group_ali);
        groupTrans = findViewById(R.id.group_trans);
        groupRef = findViewById(R.id.group_ref);

        /*teste*/

        vale_refeicao = findViewById(R.id.spinner);

        //lê spinner
        spinner = findViewById(R.id.edit_Plan);

       /* ArrayAdapter<CharSequence> array = ArrayAdapter.createFromResource(this, R.array.plano, android.R.layout.simple_spinner_item);
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(array);
        spinner.setOnItemSelectedListener(this);*/


        calcular.setOnClickListener(v->{
            if(editSalBrut.getText().toString().isEmpty()){
                editSalBrut.setError("informe um salário");
                Toast.makeText(getBaseContext(), "Informe um salário", Toast.LENGTH_SHORT).show();
            }else if(editNumDep.getText().toString().isEmpty()){
                editNumDep.setError("Informe um número");
                Toast.makeText(getBaseContext(), "Informe um número", Toast.LENGTH_SHORT).show();
            }else if(groupAli.getCheckedRadioButtonId() == -1){
                Toast.makeText(getBaseContext(), "Selecione sim ou não para vale Alimentação", Toast.LENGTH_SHORT).show();
            }else if(groupTrans.getCheckedRadioButtonId() == -1){
                Toast.makeText(getBaseContext(), "Selecione sim ou não para Vale transporte", Toast.LENGTH_SHORT).show();
            }else if(groupRef.getCheckedRadioButtonId() == -1){
                Toast.makeText(getBaseContext(), "Selecione sim ou não para Vale Refeição", Toast.LENGTH_SHORT).show();
            }else {
                double salBrut = Double.parseDouble(editSalBrut.getText().toString());
                int dependentes = Integer.parseInt(editNumDep.getText().toString());

                //radiogroup condição Vale refeição

                switch (groupRef.getCheckedRadioButtonId()) {
                    case R.id.checkbox_simRef:
                        if (salBrut <= 3000) {
                            valRef = 2.60 * 22;
                        } else if (salBrut <= 5000) {
                            valRef = 3.65 * 22;
                        } else {
                            valRef = 6.50 * 22;
                        }
                        break;
                    case R.id.checkbox_noRef:
                        valRef = 0;
                        break;
                }


                //radiogroup condição Vale transporte


                switch (groupTrans.getCheckedRadioButtonId()) {
                    case R.id.checkbox_simTrans:
                        valTrp = salBrut * 0.06;
                        break;
                    case R.id.checkbox_noTrans:
                        valTrp = 0;
                        break;
                }


                //radiogroup condição Vale alimentação

                switch (groupAli.getCheckedRadioButtonId()) {
                    case R.id.checkbox_simAlim:
                        if (salBrut <= 3000) {
                            valAlm = 15;
                        } else if (salBrut <= 5000) {
                            valAlm = 25;
                        } else {
                            valAlm = 35;
                        }
                        break;
                    case R.id.checkbox_noAlim:
                        valAlm = 0;
                        break;
                }


                //logica para o spinner plano de saude

                if (spinner.getSelectedItemPosition() == 0) {
                    if (salBrut <= 3000) {
                        plnSaud = 60;
                    } else {
                        plnSaud = 80;
                    }

                } else if (spinner.getSelectedItemPosition() == 1) {
                    if (salBrut <= 3000) {
                        plnSaud = 80;
                    } else {
                        plnSaud = 110;
                    }

                } else if (spinner.getSelectedItemPosition() == 2) {
                    if (salBrut <= 3000) {
                        plnSaud = 95;
                    } else {
                        plnSaud = 135;
                    }

                } else {
                    if (salBrut <= 3000) {
                        plnSaud = 130;
                    } else {
                        plnSaud = 180;
                    }
                }


                //INSS

                if (salBrut <= 1212) {
                    inss = salBrut * 0.075;
                    inss = inss - 90.9;
                } else if (salBrut <= 2427.35) {
                    inss = salBrut * 0.09;
                    inss = inss - 109.38;
                } else if (salBrut <= 3641.03) {
                    inss = salBrut * 0.12;
                    inss = inss - 145.64;
                } else if (salBrut <= 7087.22) {
                    inss = salBrut * 0.14;
                    inss = inss - 482.46;
                } else {
                    inss = 828.39;
                }


                //IRRF

                irrf = salBrut - inss - (189.59 * dependentes);

                if (irrf <= 1903.98) {
                    irrf = 0;
                } else if (irrf <= 2826.65) {
                    irrf *= 0.075;
                    irrf -= 142.80;
                } else if (irrf <= 3751.05) {
                    irrf *= 0.15;
                    irrf -= 354.80;
                } else if (irrf <= 4664.68) {
                    irrf *= 0.225;
                    irrf -= 636.13;
                } else {
                    irrf *= 0.275;
                    irrf -= 869.36;
                }

                double salLiq = salBrut - plnSaud - valTrp - valAlm - valRef - irrf - inss;

                Intent intencao = new Intent(this, ResultadoActivity.class);
                intencao.putExtra("valRef", String.format(" R$ %.2f", valRef));
                intencao.putExtra("valAlm", String.format(" R$ %.2f", valAlm));
                intencao.putExtra("valTrp", String.format(" R$ %.2f", valTrp));
                intencao.putExtra("plnSaud", String.format(" R$ %.2f", plnSaud));
                intencao.putExtra("inss", String.format(" R$ %.2f", inss));
                intencao.putExtra("irrf", String.format(" R$ %.2f", irrf));
                intencao.putExtra("salBrut", String.format(" R$ %.2f", salBrut));
                intencao.putExtra("salLiq", String.format(" R$ %.2f", salLiq));

                startActivity(intencao);
            }
        });

    }




   /* @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/



}

