package com.example.android.chatmate;

public class OneMessage {
    private String Message;
    private String time;
    OneMessage(String Message, String time){
        this.Message = Message;
        this.time = time;
    }

    public String getMessage() {
        return Message;
    }

    public String getTime() {
        return time;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
