package com.example.android.miwok;

import android.media.Image;
import android.widget.ImageView;
import android.widget.Switch;

public class Word {
    private String trans,englis;
    private int pic,sound;
    Word(String a, String b, int x,int y) {
        trans = a;
        englis = b;
        pic = x;
        sound = y;
    }
    Word(String a,String b,int x){
        trans = a;
        englis = b;
        sound = x;
    }

    public String getEnglis() {
        return englis;
    }

    public String getTrans() {
        return trans;
    }

    public int getPic() { return pic; }

    public int getSound() {
        return sound;
    }
}
