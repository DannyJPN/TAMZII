package com.example.netactivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import static java.security.AccessController.getContext;

public class ConvertActivity extends Activity {

    private static final int FIRST_CURRENCY = 1;
    private static final int SECOND_CURRENCY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);


        LinearLayout toplay = (LinearLayout)findViewById(R.id.LineLay1);
        LinearLayout bottomlay = (LinearLayout)findViewById(R.id.LineLay2);
        toplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(getApplicationContext(), NetworkActivity.class);
                    startActivityForResult(i, FIRST_CURRENCY);
                   
                }
                catch(Exception e)
                {

                   
                }


            }
        });


        bottomlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(getApplicationContext(), NetworkActivity.class);
                    startActivityForResult(i, SECOND_CURRENCY);

                }
                catch(Exception e)
                {


                }


            }
        });


        final EditText topedit = (EditText)findViewById(R.id.editText1);
        final EditText bottomedit = (EditText)findViewById(R.id.editText1);

        topedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                NumberFormat nf = NumberFormat.getInstance(new Locale("cs", "CZ"));
                try {

                    double toptext = nf.parse(topedit.getText().toString()).doubleValue();
                    TextView topPrice = (TextView)findViewById(R.id.txtPrice1);
                    TextView bottomPrice = (TextView)findViewById(R.id.txtPrice2);
                    double ratio = ( nf.parse(topPrice.getText().toString()).doubleValue()) / ( nf.parse(bottomPrice.getText().toString()).doubleValue());
                    double result = toptext*ratio;
                    bottomedit.setText(String.valueOf(result));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        bottomedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                NumberFormat nf = NumberFormat.getInstance(new Locale("cs", "CZ"));
                try {
                    double bottomtext =(double) nf.parse(bottomedit.getText().toString());
                    TextView topPrice = (TextView)findViewById(R.id.txtPrice1);
                    TextView bottomPrice = (TextView)findViewById(R.id.txtPrice2);
                    double ratio = ((double) nf.parse(bottomPrice.getText().toString())) / ((double) nf.parse(topPrice.getText().toString()));
                    double result = bottomtext*ratio;
                    topedit.setText(String.valueOf(result));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EntryHolder holder = new EntryHolder();
        if(resultCode != Activity.RESULT_OK)
        {
            return;
        }
        switch(requestCode)
        {

            case FIRST_CURRENCY:
            {
                holder.txtKod = (TextView)findViewById(R.id.txtKod1);
                holder.txtCountry = (TextView)findViewById(R.id.txtCountry1);
                holder.txtPrice = (TextView)findViewById(R.id.txtPrice1);
                holder.flag = (ImageView)findViewById(R.id.flagImage1);


                holder.txtKod.setText(data.getExtras().getString("code"));
                holder.txtCountry.setText(data.getExtras().getString("country"));
                holder.txtPrice.setText(String.valueOf(data.getExtras().getDouble("price")));
                holder.flagid=data.getExtras().getInt("flagid");

                holder.flag.setImageResource(holder.flagid);


                break;

            }
            case SECOND_CURRENCY:
        {
            holder.txtKod = (TextView)findViewById(R.id.txtKod2);
            holder.txtCountry = (TextView)findViewById(R.id.txtCountry2);
            holder.txtPrice = (TextView)findViewById(R.id.txtPrice2);
            holder.flag = (ImageView)findViewById(R.id.flagImage2);

            holder.txtKod.setText(data.getExtras().getString("code"));
            holder.txtCountry.setText(data.getExtras().getString("country"));
            holder.txtPrice.setText(String.valueOf(data.getExtras().getDouble("price")));
            holder.flagid=data.getExtras().getInt("flagid");

            holder.flag.setImageResource(holder.flagid);

            break;

        }
           default:{break;}
        }



    }
    static class EntryHolder
    {
        int flagid;
        TextView txtKod;
        TextView txtCountry;
        TextView txtPrice;
        ImageView flag;
    }
}


