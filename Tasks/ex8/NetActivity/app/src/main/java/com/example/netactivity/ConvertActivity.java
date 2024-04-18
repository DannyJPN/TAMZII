package com.example.netactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ConvertActivity extends AppCompatActivity {

    private static final int FIRST_CURRENCY = 1;
    private static final int SECOND_CURRENCY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);


        LinearLayout toplay = (LinearLayout)findViewById(R.id.LineLay1);
        LinearLayout bottomlay = (LinearLayout)findViewById(R.id.LineLay2);
        toplay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try {
                    Intent i = new Intent(getApplicationContext(), NetworkActivity.class);
                    startActivityForResult(i, FIRST_CURRENCY);
                    return true;
                }
                catch(Exception e)
                {

                    return false;
                }


            }
        });


        bottomlay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try {
                    Intent i = new Intent(getApplicationContext(), NetworkActivity.class);
                    startActivityForResult(i, SECOND_CURRENCY);
                    return true;
                }
                catch(Exception e)
                {

                    return false;
                }


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EntryHolder holder = new EntryHolder();
        
        switch(requestCode)
        {

            case FIRST_CURRENCY:
            {
                holder.txtKod = (TextView)findViewById(R.id.txtKod1);
                holder.txtCountry = (TextView)findViewById(R.id.txtCountry1);
                holder.txtPrice = (TextView)findViewById(R.id.txtPrice1);
                holder.flag = (ImageView)findViewById(R.id.flagImage1);
                break;

            }
            case SECOND_CURRENCY:
        {
            holder.txtKod = (TextView)findViewById(R.id.txtKod2);
            holder.txtCountry = (TextView)findViewById(R.id.txtCountry2);
            holder.txtPrice = (TextView)findViewById(R.id.txtPrice2);
            holder.flag = (ImageView)findViewById(R.id.flagImage2);
            break;

        }
           default:{break;}
        }



    }
    static class EntryHolder
    {
        TextView txtKod;
        TextView txtCountry;
        TextView txtPrice;
        ImageView flag;
    }
}


