package com.example.fifthmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        SokoView sokoview = (SokoView)(findViewById(R.id.sokogame));

        Intent receiver = getIntent();
        int lx=receiver.getExtras().getInt("LX");
        int ly=receiver.getExtras().getInt("LY");
        int[] map = receiver.getExtras().getIntArray("MAP");
        int herol = 0;
        int prevbitmap = 4;
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
        sokoview.set(herol,prevbitmap,map,lx,ly);


    }
}
