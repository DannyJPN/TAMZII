package com.example.secondmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayBMIActivity extends AppCompatActivity {

    Intent resintent;
    double result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bmi);
       Button backbutton = (Button)findViewById(R.id.backbutton);
        resintent = getIntent();
        double height= getIntent().getExtras().getDouble("Height");
        double weight= getIntent().getExtras().getDouble("Weight");
        result= Math.round(height/Math.pow(weight,2)*100.0)/100.0;

        ((TextView)findViewById(R.id.heightDisplay)).setText(new StringBuilder().append(getResources().getString(R.string.DisplayContentHeight)).append(height).toString());
        ((TextView)findViewById(R.id.weightDisplay)).setText(new StringBuilder().append(getResources().getString(R.string.DisplayContentWeight)).append(weight).toString());
        ((TextView)findViewById(R.id.BMIdisplayer)).setText(new StringBuilder().append(getResources().getString(R.string.LabelContent3)).append(result).toString());

        backbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                resintent.putExtra("RESULT",result);
                setResult(RESULT_OK,resintent);
                Log.d("Finishtag","BMIDISPLAY");
                finish();
            }
        } );

    }


}
