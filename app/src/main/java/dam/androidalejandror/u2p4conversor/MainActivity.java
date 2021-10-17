package dam.androidalejandror.u2p4conversor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends LogActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
    }

    private void setUI() {
        EditText etPulgada = findViewById(R.id.et_Pulgada);
        EditText etResultado = findViewById(R.id.et_Resultado);
        Button buttonConvertir = findViewById(R.id.button_Convertir);
        // TODO: Ex1 Two-way conversion inch <-> cm
        Button buttonConvertirCm = findViewById(R.id.button_Convertir2);
        // TODO: Ex2 Numbers < 1 Exception
        TextView tvException = findViewById(R.id.et_Exception);

        buttonConvertir.setOnClickListener(view -> {
            Log.i(super.getDEBUG_TAG(),"Boton convertir a pulgadas"); // TODO: Ex3 Log Activity Life-Cycle
            try {
                etResultado.setText(convertirAPulgadas(etPulgada.getText().toString())+" cm");
                tvException.setText("");
                // TODO: Ex2 Numbers < 1 Exception
            }catch (Exception e){
                if (e.getMessage().equals("Sólo números >=1"))
                tvException.setText(e.getMessage());
                Log.e("LongConversor", e.getMessage());
            }
        });
        // TODO: Ex1 Two-way conversion inch <-> cm
        buttonConvertirCm.setOnClickListener(view -> {
            Log.i(super.getDEBUG_TAG(),"Boton convertir a centimetros"); // TODO: Ex3 Log Activity Life-Cycle
            try {
                etResultado.setText(convertirACentimetros(etPulgada.getText().toString())+" inch");
                tvException.setText("");
                // TODO: Ex2 Numbers < 1 Exception
            }catch (Exception e){
                if (e.getMessage().equals("Sólo números >=1"))
                tvException.setText(e.getMessage());
                Log.e("LongConversor", e.getMessage());
            }
        });


    }

    private String convertirAPulgadas(String pulgadaText) throws Exception {
        comprobarExcepcion(pulgadaText);
        double pulgadaValue = Double.parseDouble(pulgadaText) * 2.54;
        double redondeado = Math.round(pulgadaValue*100.0)/100.0;
        return String.valueOf(redondeado);
    }
    // TODO: Ex1 Two-way conversion inch <-> cm
    private String convertirACentimetros(String centimetroText) throws Exception {
        comprobarExcepcion(centimetroText);
        double centimetroValue = Double.parseDouble(centimetroText) / 2.54;
        double redondeado = Math.round(centimetroValue*100.0)/100.0;
        return String.valueOf(redondeado);
    }
    // TODO: Ex2 Numbers < 1 Exception
    private void comprobarExcepcion(String texto) throws Exception {
        if (Double.parseDouble(texto) < 1){
            throw new Exception("Sólo números >=1");
        }
    }
}