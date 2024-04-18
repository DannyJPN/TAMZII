package com.example.netactivity;

import android.media.Image;
import android.widget.ImageView;

/*



*/
public class Entry {
    public final String kod;
    public final String stat;
    public final double cena;

    public final String mena;

    // TODO 3. Rozsirit dalsi udaje ve tride, ktere se budou vest pro kazdou menu
    // TODO 3. To zahrnuje i upraveni konstruktoru
            
    Entry(String kod,String stat,String mena,double cena) {
        this.kod = kod;
        this.stat=stat;
        this.cena=cena;

        this.mena=mena;

    }

}
