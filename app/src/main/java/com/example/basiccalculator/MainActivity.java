package com.example.basiccalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    float f1,f2,f;
    Boolean add,sub,mul,div;
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
        Button b1 = findViewById(R.id.b1);
        Button b2 = findViewById(R.id.b2);
        Button b3 = findViewById(R.id.b3);
        Button b4 = findViewById(R.id.b4);
        Button b5 = findViewById(R.id.b5);
        Button b6 = findViewById(R.id.b6);
        Button b7 = findViewById(R.id.b7);
        Button b8 = findViewById(R.id.b8);
        Button b9 = findViewById(R.id.b9);
        Button b0 = findViewById(R.id.b0);
        Button badd = findViewById(R.id.bplus);
        Button bsub = findViewById(R.id.bsub);
        Button bmul = findViewById(R.id.bmultiple);
        Button bdiv = findViewById(R.id.bdiv);
        Button bequal = findViewById(R.id.bequal);
        Button bdot = findViewById(R.id.bdot);
        Button bc = findViewById(R.id.bc);
        Button bac = findViewById(R.id.bac);
// Create Object for Edittext
        EditText et = findViewById(R.id.edit);

b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        et.setText(et.getText()+"1");

    }
});
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText(et.getText()+"1");

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText(et.getText()+"2");

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText(et.getText()+"3");

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText(et.getText()+"4");

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText(et.getText()+"5");

            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText(et.getText()+"6");

            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText(et.getText()+"7");

            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText(et.getText()+"8");

            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText(et.getText()+"9");

            }
        });
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText(et.getText()+"0");

            }
        });

        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText("");

            }
        });
        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText("");

            }
        });
        bdot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText(".");

            }
        });

        // Logics for add,sub,mul,div

      badd.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              f1 = Float.parseFloat(et.getText().toString());
              et.setText("");
              add = true;

          }
      });
        bsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f1 = Float.parseFloat(et.getText().toString());
                et.setText("");
                sub = true;

            }
        });

        bmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f1 = Float.parseFloat(et.getText().toString());
                et.setText("");
                mul = true;

            }
        });

        bdiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f1 = Float.parseFloat(et.getText().toString());
                et.setText("");
                div = true;

            }
        });

        // equals
        bequal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(add==true){
                f2 = Float.parseFloat(et.getText().toString());
                f = f1+ f2;
                String err = String.valueOf(f);
                et.setText(err);
                add = false;
                }
                else if (sub == true){

                    f2 = Float.parseFloat(et.getText().toString());
                    f = f1 - f2;
                    String err = String.valueOf(f);
                    et.setText(err);
                    sub = false;

                }
                else if (mul == true) {
                    f2 = Float.parseFloat(et.getText().toString());
                    f = f1 * f2;
                    String err = String.valueOf(f);
                    et.setText(err);
                    mul = false;

                }
                else if (div == true) {
                    f2 = Float.parseFloat(et.getText().toString());
                    f = f1 / f2;
                    String err = String.valueOf(f);
                    et.setText(err);
                    div = false;

                }
            }
        });

    }
}