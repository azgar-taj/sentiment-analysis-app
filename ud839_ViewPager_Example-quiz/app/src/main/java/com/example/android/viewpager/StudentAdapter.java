package com.example.android.viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter{

    public StudentAdapter(@NonNull Context context, ArrayList<student> studs) {
        super(context,0, studs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View reqlist = convertView;
        if(reqlist==null){
            reqlist = LayoutInflater.from(getContext()).inflate(R.layout.word_view,parent,false);
        }

        student s = (student) getItem(position);

        TextView txv1 = (TextView)reqlist.findViewById(R.id.name);
        txv1.setText(s.getName());

        TextView txv2 = (TextView)reqlist.findViewById(R.id.roll);
        txv2.setText(s.getRollno());

        return reqlist;
    }
}
