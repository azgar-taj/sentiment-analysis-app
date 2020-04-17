package com.example.android.chatmate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
class Document {
    public String id, language, text;

    public Document(String id, String language, String text){
        this.id = id;
        this.language = language;
        this.text = text;
    }
}

class Documents {
    public List<Document> documents;

    public Documents() {
        this.documents = new ArrayList<Document>();
    }
    public void add(String id, String language, String text) {
        this.documents.add (new Document (id, language, text));
    }
}

public class ConversationContainer extends AppCompatActivity {
    private EditText message_sent;
    private ListView message_list;
    private message_list_adapter adapter;
    ArrayList<OneMessage> messages;
    static String endpoint;
    static String subscription_key;
    static String path = "/text/analytics/v3.0-preview/sentiment";
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
                OneMessage message = new OneMessage(message_sent.getText().toString(),getFormattedTime(),Constants.OUTBOUND);
                messages.add(message);
                message_sent.setText("");
                updateConversation();
                Documents documents = new Documents();
                documents.add("1","en",message.getMessage());
                message_reply reply = new message_reply();
                reply.execute(documents);
                //StartTA(message);
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
    /*
    public void StartTA(OneMessage message){
        String response = TextAnalyticsCustom.senti(message.getMessage());
        while(response==null){}
        OneMessage response_message = new OneMessage(response,getFormattedTime());
        messages.add(response_message);
        updateConversation();
    }

     */

    public class message_reply extends AsyncTask<Documents,Integer,ArrayList<OneMessage>>{

        @Override
        protected ArrayList<OneMessage> doInBackground(Documents... documents) {
            subscription_key = Constants.KEY;
            endpoint = Constants.ENDPOINT;
            String text = (new Gson().toJson(documents));
            text = text.substring(1,text.length()-1);
            BufferedReader in = null;
            StringBuilder response = new StringBuilder();
            try {
                byte[] encoded_text = text.getBytes("UTF-8");

                URL url = new URL(endpoint + path);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "text/json");
                connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscription_key);
                connection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
                connection.setRequestProperty("Accept","*/*");
                connection.setDoOutput(true);

                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.write(encoded_text, 0, encoded_text.length);
                wr.flush();
                wr.close();
                int sc = connection.getResponseCode();
                InputStream err = connection.getErrorStream();
                InputStream inr = connection.getInputStream();

                in = new BufferedReader(
                        new InputStreamReader(inr));
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                return ConvertToMessages(response.toString());
            }

        }

        @Override
        protected void onPostExecute(ArrayList<OneMessage> oneMessages) {
            for(OneMessage i : oneMessages) {
                messages.add(i);
            }
            updateConversation();
        }
    }
    public ArrayList<OneMessage> ConvertToMessages(String Response){
        ArrayList<OneMessage> messages = new ArrayList<>();
        try {
            JSONObject JSONResponse = new JSONObject(Response);
            JSONArray documents = JSONResponse.getJSONArray("documents");
            for(int i = 0; i < documents.length(); i++){
                messages.add(new OneMessage(documents.getJSONObject(i).getString("sentiment"), getFormattedTime(),Constants.INBOUND));
            }
        }
        catch (Exception e){e.printStackTrace();}
        finally {
            return messages;
        }
    }
}
