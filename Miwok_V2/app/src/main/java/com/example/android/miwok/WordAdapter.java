package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    int kalor;
    public WordAdapter(Activity context, ArrayList<Word> words,int kalor) {
        super(context,0, words);
        this.kalor = kalor;
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }

        Word current = getItem(position);

        TextView trax = (TextView)listItemView.findViewById(R.id.mivo);
        trax.setText(current.getTrans());

        TextView engs = (TextView)listItemView.findViewById(R.id.transu);
        engs.setText(current.getEnglis());

        ImageView imgs = (ImageView)listItemView.findViewById(R.id.imgr);
        if(current.getPic()!=0)
            imgs.setImageResource(current.getPic());

        LinearLayout lr = (LinearLayout) listItemView.findViewById(R.id.kolor);
        lr.setBackgroundColor(ContextCompat.getColor(getContext(),kalor));

        return listItemView;
    }
}
