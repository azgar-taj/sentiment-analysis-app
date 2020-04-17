package com.example.android.chatmate;

public class OneMessage {
    private String Message;
    private String time;
    private int direction;
    OneMessage(String Message, String time,int direction){
        this.Message = Message;
        this.time = time;
        this.direction = direction;
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

    public int getDirection() {
        return direction;
    }
}
