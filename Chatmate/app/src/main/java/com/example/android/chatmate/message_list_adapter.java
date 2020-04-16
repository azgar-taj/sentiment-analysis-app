package com.example.android.chatmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class message_list_adapter extends ArrayAdapter {

    public message_list_adapter(@NonNull Context context, @NonNull ArrayList<OneMessage> messages) {
        super(context, 0,messages );
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View message_dialogue = convertView;
        if(message_dialogue==null){
            message_dialogue = LayoutInflater.from(getContext()).inflate(R.layout.one_message,parent,false);
        }
        TextView message_text = message_dialogue.findViewById(R.id.message_recieved_text);
        TextView message_time = message_dialogue.findViewById(R.id.message_time);

        OneMessage message = (OneMessage) getItem(position);

        message_text.setText(message.getMessage());
        message_time.setText(message.getTime());

        return message_dialogue;
    }
}
