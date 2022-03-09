package br.senai.sp.cotia.salarioliquido;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {
    private TextView salaBrut, mostraValRef, mostraValAli, mostraValTrans, mostraPlanSau, mostraInss, mostraIrrf, mostraSalliq;
    private String salBrut, planSau, valTrans, valAli, valRef, irrf, inss, salLiq;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);

        salBrut = getIntent().getStringExtra("salBrut");
        planSau = getIntent().getStringExtra("plnSaud");
        valTrans = getIntent().getStringExtra("valTrp");
        valAli = getIntent().getStringExtra("valAlm");
        valRef = getIntent().getStringExtra("valRef");
        irrf = getIntent().getStringExtra("irrf");
        inss = getIntent().getStringExtra("inss");
        salLiq = getIntent().getStringExtra("salLiq");



        salaBrut = findViewById(R.id.tv_salbrut);
        mostraPlanSau = findViewById(R.id.tv_planSau);
        mostraValTrans = findViewById(R.id.tv_valTrans);
        mostraValAli = findViewById(R.id.tv_valAli);
        mostraValRef = findViewById(R.id.tv_valRef);
        mostraIrrf = findViewById(R.id.tv_irrf);
        mostraInss = findViewById(R.id.tv_inss);
        mostraSalliq = findViewById(R.id.tv_salLiq);



        salaBrut.setText("O seu salário bruto é: R$"+ salBrut+"");
        mostraPlanSau.setText("O dseconto do plano de saude é: R$"+planSau+"");
        mostraInss.setText("O seu INSS é: R$"+inss+"");
        mostraIrrf.setText("O seu IRRF é: R$"+irrf+"");
        mostraValAli.setText("O desconto do seu Vale Alimentação é: R$"+valAli+"");
        mostraValTrans.setText("O desconto do seu Vale transporte é: R$"+valTrans+"");
        mostraValRef.setText("O desconto do seu Vale refeição é: R$"+valRef+"");
        mostraSalliq.setText("O seu salário líquido é: R$"+salLiq+"");



    }
}
