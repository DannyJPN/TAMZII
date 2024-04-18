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
        if(Arrays.asList(map).indexOf(4) !=-1 && Arrays.asList(map).indexOf(6) ==-1)
        {
            herol = Arrays.asList(map).indexOf(4);
        }
        else if (Arrays.asList(map).indexOf(6) !=-1&& Arrays.asList(map).indexOf(4) ==-1)
        {
            herol = Arrays.asList(map).indexOf(6);
            prevbitmap = 6;
        }
        else
        {
            map = new int[lx * ly];
        }
        sokoview.set(herol,prevbitmap,map,lx,ly);


    }
}
