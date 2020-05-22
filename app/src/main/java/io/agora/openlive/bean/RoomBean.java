package io.agora.openlive.bean;

public class RoomBean {

    public static final int SAFE_STATE = 0;
    public static final int ERROR_STATE = 1;

    public String name;
    public int state;
    public String code;

    public RoomBean(String name, int state, String code) {
        this.state = state;
        this.name = name;
        this.code = code;
    }
}
