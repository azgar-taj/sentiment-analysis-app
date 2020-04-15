package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import android.support.v4.content.ContextCompat;
public class entryAdapter extends ArrayAdapter {

    public entryAdapter(@NonNull Context context, ArrayList<entry_placeholder> entries) {
        super(context, 0,entries);
    }

    private static String formatDate(long time) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormatter.format(time);
    }

    private static String formatTime(long time) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm a");
        return dateFormatter.format(time);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    private static String formatMag(String mag){
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(Double.parseDouble(mag));
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View reqV = convertView;
        if(reqV==null){
            reqV = LayoutInflater.from(getContext()).inflate(R.layout.one_entry,parent,false);
        }

        entry_placeholder x = (entry_placeholder) getItem(position);

        String place ="";
        String dist = "";

        String req = x.getPlace();

        if(req.indexOf(" of ")!=-1){
            place = req.substring(req.indexOf(" of ")+4);
            dist = req.substring(0,req.indexOf(" of ")+4);
        }
        else{
            place = req;
            dist = "Near the";
        }

        TextView txt1 = (TextView) reqV.findViewById(R.id.index);
        txt1.setText(formatMag(x.getIndex()));

        TextView txt2 = (TextView) reqV.findViewById(R.id.place);
        txt2.setText(place);

        TextView txt5 = (TextView) reqV.findViewById(R.id.dist);
        txt5.setText(dist);

        TextView txt3 = (TextView) reqV.findViewById(R.id.date);
        txt3.setText(formatDate(Long.parseLong(x.getDate())));

        TextView txt4 = (TextView) reqV.findViewById(R.id.time);
        txt4.setText(formatTime(Long.parseLong(x.getDate())));


        GradientDrawable magnitudeCircle = (GradientDrawable) txt1.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(Double.parseDouble(x.getIndex().trim()));

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);



        return reqV;
    }
}
