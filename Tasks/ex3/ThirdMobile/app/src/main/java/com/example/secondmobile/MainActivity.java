package com.example.secondmobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.drm.DrmStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    final int BMICALC = 1;
    final int PHOTO = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button calcbutton = (Button)findViewById(R.id.calcbutton);

        calcbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(v.getId() == R.id.calcbutton)
                {   double height = Double.parseDouble(((EditText)findViewById(R.id.heightedit)).getText().toString());
                    double weight = Double.parseDouble(((EditText)findViewById(R.id.weightedit)).getText().toString());
    /*                double result = Math.round(height/Math.pow(weight,2)*100.0)/100.0;
                  ((TextView)findViewById(R.id.BMItext)).setText(new StringBuilder().append(getResources().getString(R.string.LabelContent3)).append(result).toString());
                    ((ImageView)findViewById(R.id.resultimageView)).setImageResource(R.drawable.happysmiley);
*/
                    Intent calcintent = new Intent(getApplicationContext(), DisplayBMIActivity.class);

                    calcintent.putExtra("Height",height);
                    calcintent.putExtra("Weight",weight);
                    //calcintent.putExtra("BMI",result);

                    startActivityForResult(calcintent,BMICALC);
                }
            }
        });





    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("BMI","request code:" +String.valueOf(requestCode)+" data: "+String.valueOf(data));
        ((TextView)findViewById(R.id.BMItext)).setText(new StringBuilder().append(getResources().getString(R.string.LabelContent3)).append(data.getExtras().getDouble("RESULT")).toString());
    }
}
