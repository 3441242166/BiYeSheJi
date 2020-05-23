package io.agora.openlive.bean;

public class RoomBean implements Comparable<RoomBean>{

    public static final int SAFE_STATE = 0;
    public static final int ERROR_STATE = 1;

    public String name;
    public int state;
    public String code;
    public boolean isTop;

    public RoomBean(String name, int state, String code) {
        this.state = state;
        this.name = name;
        this.code = code;
        isTop = false;
    }

    @Override
    public int compareTo(RoomBean bean) {
        if (isTop && !bean.isTop) {
            return -1;
        } else {
            return 1;
        }
    }
}
