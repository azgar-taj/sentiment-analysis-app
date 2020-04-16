package com.example.android.chatmate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ConversationContainer extends AppCompatActivity {
    private EditText message_sent;
    private ListView message_list;
    private message_list_adapter adapter;
    ArrayList<OneMessage> messages;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation_box);
        message_sent = findViewById(R.id.send_message_text);
        Button send_button = findViewById(R.id.send_button);
        message_list = findViewById(R.id.messages_list);
        messages = new ArrayList<>();
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messages.add(new OneMessage(message_sent.getText().toString(),getFormattedTime()));
                message_sent.setText("");
                updateConversation();
            }
        });
    }

    public void updateConversation(){
        adapter = new message_list_adapter(getApplicationContext(),messages);
        message_list.setAdapter(adapter);
    }

    public String getFormattedTime(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm");
            return myDateObj.format(myFormatObj);
        }
        else{
            return "Default time";
        }
    }

}
