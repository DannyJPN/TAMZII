package com.example.secondmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button calcbutton = (Button)findViewById(R.id.calcbutton);

        calcbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double height = Double.parseDouble(((EditText)findViewById(R.id.heightedit)).getText().toString());
                double weight = Double.parseDouble(((EditText)findViewById(R.id.weightedit)).getText().toString());
                double result = Math.round(height/Math.pow(weight,2)*100.0)/100.0;
                ((TextView)findViewById(R.id.BMItext)).setText(new StringBuilder().append(getResources().getString(R.string.LabelContent3)).append(result).toString());
                ((ImageView)findViewById(R.id.resultimageView)).setImageResource(R.drawable.happysmiley);

            }
        });

    }
}
