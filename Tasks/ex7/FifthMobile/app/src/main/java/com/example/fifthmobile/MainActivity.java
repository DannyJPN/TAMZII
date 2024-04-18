package com.example.fifthmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ngbutton = (Button)findViewById(R.id.newgame);
        ngbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LevelActivity.class));
            }
        });
        Button resumebutton = (Button)findViewById(R.id.resumer);
        resumebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // SokoView sokoview = (SokoView)(findViewById(R.id.sokogame));
                int lx=0,ly=0;
                int[] map=new int[0];
                List<String> level = new ArrayList<>();
                try
                {
                    InputStream is = openFileInput("currently_saved_level.txt");

                    DataInputStream dis = new DataInputStream(is);
                    while(dis.available() >0) {
                        StringBuilder line = new StringBuilder("");
                        char s;
                        while (!((s = (char) dis.readByte()) == '\n') ) {
                            if(s!='\r') {
                                line.append(s);
                            }
                        }
                        Log.d("Level debugger:",line.toString());

                        String realline = line.toString();

                        if (realline.matches("Level [0-9]*")) {
                            Log.d("Level header:", realline.split(" ")[1]);
                            level = new ArrayList<String>();



                        } else if (realline.matches("[#@ .$+*]*") && realline.length() > 0) {
                            Log.d("Level line:", realline);
                            realline=realline.replace('#', '1');
                            realline=realline.replace('$', '2');
                            realline=realline.replace('.', '3');
                            realline=realline.replace('@', '4');
                            realline=realline.replace('+', '6');
                            realline=realline.replace('*', '5');
                            realline=realline.replace(' ', '0');

                            level.add(realline);


                        } else if (realline.length() == 0 || realline.matches("[ ]*")) {

                            Log.d("Level borderline:", realline);
                        }
                        else
                        {
                            Log.d("Level line unknown:", realline);

                        }


                    }


                    int maxlen = 0;
                    for(int j = 0;j<level.size();j++)
                    {
                        if(maxlen <level.get(j).length())
                        {
                            maxlen =level.get(j).length();

                        }

                    }
                    Log.d("Level MAXLEN:","RESUMED");
                    Log.d("Level MAXLEN linelen:",String.valueOf(maxlen));

                    for(int j = 0;j<level.size();j++)
                    {
                        StringBuilder mid = new StringBuilder(level.get(j));
                        while(maxlen >mid.length()) {
                            mid.append("0");
                            level.set(j,mid.toString());
                        }
                        Log.d("Level MAXLEN line:", level.get(j));


                    }

                    lx=maxlen;
                    ly=level.size();
                    StringBuilder levelline = new StringBuilder("");
                    for(int j = 0;j<level.size();j++)
                    {

                        levelline.append(level.get(j));


                    }

                    map = new int[levelline.length()];
                    for(int j =0;j<levelline.length();j++)
                    {

                        map[j]=Integer.parseInt(String.valueOf(levelline.toString().charAt(j)));

                    }









                } catch (IOException e) {
                    e.printStackTrace();
                }


                int herol = 0;
                int prevbitmap = 0;
                int heroindex = -1;
                int heroongoldindex=-1;

                for(int i =0;i<map.length;i++)
                {
                    if(map[i] == 4)
                    {
                        heroindex=i;
                    }
                    else if(map[i] ==6)
                    {
                        heroongoldindex=i;

                    }

                }
                if(heroindex !=-1 && heroongoldindex ==-1)
                {
                    herol = heroindex;
                }
                else if (heroongoldindex !=-1&& heroindex ==-1)
                {
                    herol = heroongoldindex;
                    prevbitmap = 3;
                }
                else
                {
                    map = new int[lx * ly];
                }

                //sokoview.set(herol,prevbitmap,map,lx,ly);

                Intent levelgiver = new Intent(getApplicationContext(),GameActivity.class);
                levelgiver.putExtra("LX",lx);
                levelgiver.putExtra("LY",ly);
                levelgiver.putExtra("MAP",map);
                startActivity(levelgiver);


            }
        });



    }
}
