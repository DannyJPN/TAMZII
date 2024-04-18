package com.example.fifthmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class LevelActivity extends AppCompatActivity {

    private List<SokoLevel> sokolevels ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);


        sokolevels = new ArrayList<>();
        List<ArrayList<String>> levels = new ArrayList<>();
        try
        {
            InputStream is = getResources().openRawResource(R.raw.levels);
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
                    levels.add(new ArrayList<String>());


                } else if (realline.matches("[#@ .$+*]*") && realline.length() > 0) {
                    Log.d("Level line:", realline);
                    realline=realline.replace('#', '1');
                    realline=realline.replace('$', '2');
                    realline=realline.replace('.', '3');
                    realline=realline.replace('@', '4');
                    realline=realline.replace('+', '6');
                    realline=realline.replace('*', '5');
                    realline=realline.replace(' ', '0');

                    levels.get(levels.size() - 1).add(realline);


                } else if (realline.length() == 0 || realline.matches("[ ]*")) {

                    Log.d("Level borderline:", realline);
                }
                else
                {
                    Log.d("Level line unknown:", realline);

                }


            }

            for(int i =0;i<levels.size();i++)
            {
                int maxlen = 0;
                for(int j = 0;j<levels.get(i).size();j++)
                {
                    if(maxlen <levels.get(i).get(j).length())
                    {
                        maxlen =levels.get(i).get(j).length();

                    }

                }

                for(int j = 0;j<levels.get(i).size();j++)
                {

                        while(maxlen <levels.get(i).get(j).length()) {
                            levels.get(i).set(j,levels.get(i).get(j)+"0");
                        }



                }

                SokoLevel sok = new SokoLevel();
                sok.lx=maxlen;
                sok.ly=levels.get(i).size();
                StringBuilder levelline = new StringBuilder("");
                for(int j = 0;j<levels.get(i).size();j++)
                {

                    levelline.append(levels.get(i).get(j));


                }
                sok.map = new int[levelline.length()];
                for(int j =0;j<levelline.length();j++)
                {

                    sok.map[j]=Integer.parseInt(String.valueOf(levelline.toString().charAt(j)));

                }
                    sokolevels.add(sok);





            }


        }
        catch(Exception e)
        {
            e.printStackTrace();

        }








        final List<String> lisviewstrings = new ArrayList<>();
        for (int i = 0; i < sokolevels.size(); i++) {
            lisviewstrings.add("level " + String.valueOf(i+1));

        }
        ListView listview = (ListView) findViewById(R.id.listview);
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, lisviewstrings);
        listview.setAdapter(adapter);




        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                int levindex = Integer.parseInt(item.split(" ")[1])-1;
                Intent levelgiver = new Intent(getApplicationContext(),GameActivity.class);
                levelgiver.putExtra("LX",sokolevels.get(levindex).lx);
                levelgiver.putExtra("LY",sokolevels.get(levindex).ly);
                levelgiver.putExtra("MAP",sokolevels.get(levindex).map);
                startActivity(levelgiver);
            }

        });


    }


    private class SokoLevel
    {

        public int lx=0;
        public int ly=0;
        public int[] map=new int[0];




    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}


