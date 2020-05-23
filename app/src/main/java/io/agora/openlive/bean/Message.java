package io.agora.openlive.bean;

public class Message {
    public String address;
    public String time;
    public String state;
    public String notice;

    public Message(String address, String time, String state, String notice) {
        this.address = address;
        this.time = time;
        this.state = state;
        this.notice = notice;
    }
}
